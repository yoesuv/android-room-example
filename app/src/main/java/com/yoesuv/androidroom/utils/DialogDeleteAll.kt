package com.yoesuv.androidroom.utils

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.databinding.PopupDeleteAllBinding

fun dialogDeleteAll(context: Context, onConfirm:() -> Unit) {
    var dialogDeleteAll: AlertDialog? = null
    val dialogBuilder: MaterialAlertDialogBuilder?
    val binding = PopupDeleteAllBinding.inflate(LayoutInflater.from(context))
    binding.buttonCancel.setOnClickListener {
        dialogDeleteAll?.dismiss()
    }
    binding.buttonOk.setOnClickListener {
        dialogDeleteAll?.dismiss()
        onConfirm()
    }
    dialogBuilder = MaterialAlertDialogBuilder(context, R.style.DialogTheme)
        .setView(binding.root)
    dialogDeleteAll = dialogBuilder.show()
    dialogDeleteAll?.setCancelable(false)
    dialogDeleteAll?.setCanceledOnTouchOutside(false)
}