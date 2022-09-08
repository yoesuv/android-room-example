package com.yoesuv.androidroom.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.yoesuv.androidroom.databinding.PopupMenuBinding

fun dialogMenu(context: Context, onEdit:() -> Unit, onDelete:() -> Unit) {
    var dialogMenu: AlertDialog? = null
    val dialogBuilder = AlertDialog.Builder(context)
    val binding = PopupMenuBinding.inflate(LayoutInflater.from(context))
    binding.tvEditTask.setOnClickListener {
        dialogMenu?.dismiss()
        onEdit()
    }
    binding.tvDeleteTask.setOnClickListener {
        dialogMenu?.dismiss()
        onDelete()
    }
    dialogBuilder.setView(binding.root)
    dialogMenu = dialogBuilder.create()
    dialogMenu.show()

}