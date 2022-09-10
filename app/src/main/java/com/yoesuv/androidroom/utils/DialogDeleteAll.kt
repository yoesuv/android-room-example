package com.yoesuv.androidroom.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.yoesuv.androidroom.databinding.PopupDeleteAllBinding

fun dialogDeleteAll(context: Context, onConfirm:() -> Unit) {
    var dialogDeleteAll: AlertDialog? = null
    val binding = PopupDeleteAllBinding.inflate(LayoutInflater.from(context))
    binding.buttonCancel.setOnClickListener {
        dialogDeleteAll?.dismiss()
    }
    binding.buttonOk.setOnClickListener {
        dialogDeleteAll?.dismiss()
        onConfirm()
    }
    dialogDeleteAll = AlertDialog.Builder(context)
        .setView(binding.root)
        .create()
    dialogDeleteAll?.setCancelable(false)
    dialogDeleteAll?.setCanceledOnTouchOutside(false)
    dialogDeleteAll?.show()
}