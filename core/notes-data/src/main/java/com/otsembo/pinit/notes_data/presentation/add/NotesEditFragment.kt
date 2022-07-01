package com.otsembo.pinit.notes_data.presentation.add

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.transition.TransitionInflater
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.otsembo.pinit.notes_data.common.AppResource
import com.otsembo.pinit.notes_data.data.model.AppNote
import com.otsembo.pinit.notes_data.databinding.FragmentNoteEditBinding
import com.otsembo.pinit.notes_data.presentation.viewmodels.NoteEditVM
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject

class NotesEditFragment : DialogFragment() {

    private lateinit var binding: FragmentNoteEditBinding
    private val viewModel: NoteEditVM by inject()
    private val appNote: AppNote by lazy {
        AppNote()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentNoteEditBinding.inflate(layoutInflater)
        binding.noteData = appNote
        initClicks()
        return AlertDialog.Builder(requireContext(), com.otsembo.pinit.theming.R.style.FullScreenDialog)
            .setView(binding.root)
            .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(com.otsembo.pinit.theming.R.transition.slide_up)
        isCancelable = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUiData(savedInstanceState)
        lifecycleScope.launchWhenResumed {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                initFlowObservers()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveUiData(outState)
    }

    private fun initClicks() {
        binding.imgClose.setOnClickListener {
            dismiss()
        }
        binding.txtSave.setOnClickListener {
            appNote.status = viewModel.selectStatus(binding.txtNoteStatus.text.toString())
            viewModel.createNote(appNote)
            dismiss()
        }
        binding.txtAddPhoto.setOnClickListener {
            Log.d("TAG", "initClicks: clicked")
            when {
                ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED -> { selectImage() }
                shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    messageDialog(
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

    private fun selectImage() {
        val intent = Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT)
        activityResultLauncher.launch(intent)
    }

    private suspend fun initFlowObservers() {
        viewModel.errorFlow.collectLatest {
            displayMessage(it)
        }
        viewModel.notesImageLocation.collectLatest {
            appNote.imageUrl = it
        }
        viewModel.notes.collect {
            when (it) {
                is AppResource.Idle -> {}
                is AppResource.Error -> {}
                is AppResource.Success -> {}
                is AppResource.Loading -> {}
            }
        }
    }

    private val activityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val intent = it.data
            val data = intent?.data
            val bitmap: Bitmap? = try {
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, data)
            } catch (e: Exception) { null }
            binding.noteImage.setImageBitmap(bitmap)
            if (bitmap != null) {
                lifecycleScope.launchWhenCreated {
                    viewModel.uploadImage(bitmap)
                }
            }
        }
    }

    private fun displayMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun saveUiData(bundle: Bundle) {
        bundle.putString(TITLE, appNote.noteTitle)
        bundle.putString(DESCRIPTION, appNote.description)
    }

    private fun loadUiData(bundle: Bundle?) {
        bundle?.let {
            appNote.noteTitle = it.getString(TITLE)
            appNote.description = it.getString(DESCRIPTION)
        }
    }

    private val permissionLauncher: ActivityResultLauncher<String> = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) selectImage()
        else {
            messageDialog(
                "Permission declined." +
                    " In order for you to upload images, you need to enable storage permission." +
                    "\nYou will not be able to use this feature without it.",
                actionKey = "OK", action = {}
            ).show()
        }
    }

    private fun messageDialog(message: String, actionKey: String, action: () -> Unit): AlertDialog {
        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("PERMISSION REQUEST")
            .setMessage(message)
            .setPositiveButton(actionKey) { _, _ -> action() }
            .setNegativeButton("CANCEL") { _, _ -> }
            .create()
    }

    companion object {
        const val TITLE = "title"
        const val DESCRIPTION = "desc"
        const val STATUS = "status"
    }
}
