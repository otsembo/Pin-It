package com.otsembo.notes.presentation

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.otsembo.pinit.notes_data.common.AppResource
import com.otsembo.pinit.notes_data.presentation.adapters.NotesAdapter

@BindingAdapter("dashboardLoading")
fun CircularProgressIndicator.isLoading(dataLoading: AppResource<*>) {
    this.visibility = if (dataLoading is AppResource.Loading) View.VISIBLE else View.GONE
}

@BindingAdapter("latestNotes")
fun RecyclerView.latestNotes(adapter: NotesAdapter) {
    this.adapter = adapter
}
