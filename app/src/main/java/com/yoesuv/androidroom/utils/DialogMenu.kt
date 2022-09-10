package com.yoesuv.androidroom.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.yoesuv.androidroom.databinding.PopupMenuBinding

fun dialogMenu(context: Context, onEdit:() -> Unit, onDelete:() -> Unit) {
    var dialogMenu: AlertDialog? = null
    val binding = PopupMenuBinding.inflate(LayoutInflater.from(context))
    binding.btnEditTask.setOnClickListener {
        dialogMenu?.dismiss()
        onEdit()
    }
    binding.btnDeleteTask.setOnClickListener {
        dialogMenu?.dismiss()
        onDelete()
    }
    dialogMenu = AlertDialog.Builder(context)
        .setView(binding.root)
        .create()
    dialogMenu.show()

}