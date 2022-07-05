package com.otsembo.pinit.theming.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.otsembo.pinit.theming.R

// ANOTHER UNNECESSARY ABSTRACTION
fun Fragment.mCtx(): Context {
    return requireContext()
}

fun Fragment.fullScreenDialog(view: View? = null): AlertDialog {
    return if (view == null) AlertDialog.Builder(mCtx(), R.style.FullScreenDialog).create()
    else AlertDialog.Builder(mCtx(), R.style.FullScreenDialog).setView(view).create()
}

fun Fragment.regularDialog(title: String, message: String, actionKey: String, action: () -> Unit): AlertDialog {
    return MaterialAlertDialogBuilder(mCtx())
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(actionKey) { _, _ -> action() }
        .setNegativeButton("CANCEL") { _, _ -> }
        .setCancelable(false)
        .create()
}

fun Fragment.statusDialog(title: String, message: String, actionKey: String, action: () -> Unit): AlertDialog {
    return MaterialAlertDialogBuilder(mCtx())
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(actionKey) { _, _ -> action() }
        .setCancelable(false)
        .create()
}

fun Fragment.progressDialog(): AlertDialog {
    val inflater = LayoutInflater.from(mCtx())
    val view = inflater.inflate(R.layout.dialog_loading, null, false)
    return MaterialAlertDialogBuilder(mCtx())
        .setView(view)
        .setCancelable(false)
        .create()
}
