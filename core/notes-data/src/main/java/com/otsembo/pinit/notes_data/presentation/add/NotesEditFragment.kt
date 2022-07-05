package com.otsembo.pinit.notes_data.presentation.add

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.otsembo.pinit.notes_data.common.AppResource
import com.otsembo.pinit.notes_data.databinding.FragmentNoteEditBinding
import com.otsembo.pinit.notes_data.presentation.viewmodels.NoteEditVM
import com.otsembo.pinit.theming.presentation.fullScreenDialog
import com.otsembo.pinit.theming.presentation.regularDialog
import com.otsembo.pinit.theming.presentation.statusDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class NotesEditFragment : DialogFragment() {

    private lateinit var binding: FragmentNoteEditBinding
    private val viewModel: NoteEditVM by inject()
    private lateinit var statusDialog: AlertDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentNoteEditBinding.inflate(layoutInflater)
        return fullScreenDialog(view = binding.root)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        initClicks()
        registerFlowCollectors()
        statusDialog = statusDialog(
            title = "SUCCESS", message = "Uploaded note successfully", actionKey = "DONE", action = { statusDialog.dismiss(); viewModel.closeDialog() }
        )
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUiData(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveUiData(outState)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        viewModel.resetDialogState()
    }

    // UI DATA SAVED INSTANCE
    private fun saveUiData(bundle: Bundle) {
        bundle.putString(TITLE, viewModel.noteData.value.noteTitle)
        bundle.putString(DESCRIPTION, viewModel.noteData.value.description)
    }

    private fun loadUiData(bundle: Bundle?) {
        bundle?.let {
            viewModel.noteData.value.noteTitle = it.getString(TITLE)
            viewModel.noteData.value.description = it.getString(DESCRIPTION)
        }
    }

    // INTENT GALLERY SELECTOR
    private fun selectImage() {
        val intent = Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT)
        activityResultLauncher.launch(intent)
    }

    // FLOW COLLECTORS
    private fun registerFlowCollectors() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.errorMessage.collectLatest {
                    displayMessage(it)
                }
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.isDialogClose.collect {
                if (it) this@NotesEditFragment.dismiss()
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.appState.collectLatest {
                if (it is AppResource.Success) {
                    statusDialog.show()
                }
            }
        }
    }

    // DIALOG LAUNCHERS
    private fun initClicks() {

        binding.txtAddPhoto.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED -> { selectImage() }
                shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    regularDialog(
                        title = "PERMISSION ERROR",
                        "Permission not granted." +
                            " In order for you to upload images, you need to enable storage permission." +
                            "\nYou will not be able to use this feature without it.",
                        actionKey = "GIVE PERMISSION", action = { permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE) }
                    ).show()
                }
                else -> permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    // SNACKBAR MESSAGE DISPLAY
    private fun displayMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    // ACTIVITY RESULT LAUNCHERS
    private val permissionLauncher: ActivityResultLauncher<String> = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) selectImage()
        else {
            regularDialog(
                title = "PERMISSION ERROR",
                "Permission declined." +
                    " In order for you to upload images, you need to enable storage permission." +
                    "\nYou will not be able to use this feature without it.",
                actionKey = "OK", action = {}
            ).show()
        }
    }

    private val activityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val intent = it.data
            val data = intent?.data
            val bitmap: Bitmap? = try {
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, data)
            } catch (e: Exception) { null }
            binding.noteImage.setImageBitmap(bitmap)
            if (bitmap != null) {
                viewModel.uploadImage(bitmap)
            }
        }
    }

    companion object {
        const val TITLE = "title"
        const val DESCRIPTION = "desc"
    }
}
