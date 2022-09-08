package com.yoesuv.androidroom.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.yoesuv.androidroom.databinding.PopupDeleteAllBinding

fun dialogDeleteAll(context: Context, onConfirm:() -> Unit) {
    var dialogDeleteAll: AlertDialog? = null
    val dialogBuilder = AlertDialog.Builder(context)
    val binding = PopupDeleteAllBinding.inflate(LayoutInflater.from(context))
    binding.buttonCancel.setOnClickListener {
        dialogDeleteAll?.dismiss()
    }
    binding.buttonOk.setOnClickListener {
        dialogDeleteAll?.dismiss()
        onConfirm()
    }
    dialogBuilder.setView(binding.root)
    dialogDeleteAll = dialogBuilder.create()
    dialogDeleteAll?.setCancelable(false)
    dialogDeleteAll?.setCanceledOnTouchOutside(false)
    dialogDeleteAll?.show()
}