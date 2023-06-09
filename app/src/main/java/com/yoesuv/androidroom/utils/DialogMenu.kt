package com.yoesuv.androidroom.utils


import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.databinding.PopupMenuBinding

fun dialogMenu(context: Context, onEdit:() -> Unit, onDelete:() -> Unit) {
    var dialogMenu: AlertDialog? = null
    val dialogBuilder: MaterialAlertDialogBuilder?
    val binding = PopupMenuBinding.inflate(LayoutInflater.from(context))
    binding.btnEditTask.setOnClickListener {
        dialogMenu?.dismiss()
        onEdit()
    }
    binding.btnDeleteTask.setOnClickListener {
        dialogMenu?.dismiss()
        onDelete()
    }
    dialogBuilder = MaterialAlertDialogBuilder(context, R.style.DialogTheme)
        .setView(binding.root)
    dialogMenu = dialogBuilder.show()

}