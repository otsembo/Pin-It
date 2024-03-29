package com.otsembo.pinit.notes_data.presentation

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.otsembo.pinit.notes_data.R
import com.otsembo.pinit.notes_data.common.AppResource
import com.otsembo.pinit.notes_data.common.NotesUtil
import com.otsembo.pinit.notes_data.data.model.AppNote
import com.otsembo.pinit.notes_data.presentation.adapters.NotesAdapter
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("isLoading")
fun ProgressBar.isLoading(isLoading: Boolean) {
    this.visibility = if (isLoading) View.VISIBLE else View.GONE
}

@BindingAdapter("isLoading")
fun LinearProgressIndicator.isLoading(uploadState: AppResource<String>) {
    this.visibility = if (uploadState is AppResource.Loading) View.VISIBLE else View.GONE
}

@BindingAdapter("noteImage")
fun CircleImageView.noteImage(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) this.load(R.drawable.ic_default_note)
    imageUrl?.let {
        Firebase.storage.getReferenceFromUrl(NotesUtil.getFirebaseImage(it)).downloadUrl.addOnCompleteListener { downloadTask ->
            if (downloadTask.isSuccessful) {
                this.load(downloadTask.result.toString())
            } else {
                this.load(R.drawable.ic_default_note)
            }
        }
    }
}

@BindingAdapter("notesList")
fun RecyclerView.notesAdapter(appNotes: List<AppNote>) {
    val adapter = NotesAdapter()
    adapter.submitItems(appNotes)
    Log.d("NOTES", "$appNotes")
    this.adapter = adapter
}


@BindingAdapter("latestNotes")
fun RecyclerView.latestNotes(adapter: NotesAdapter) {
    this.adapter = adapter
}
