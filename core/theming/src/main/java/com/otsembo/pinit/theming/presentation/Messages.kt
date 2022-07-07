package com.otsembo.pinit.theming.presentation

import android.view.View
import com.google.android.material.snackbar.Snackbar

// snack bars
fun View.snackIt(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}
