package com.otsembo.pinit.notes_data.presentation

import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

@BindingAdapter("isLoading")
fun ProgressBar.isLoading(isLoading: Boolean) {
    Log.d("TAG", "Data Loading: $isLoading")
    this.visibility = if (isLoading) View.VISIBLE else View.GONE
}
