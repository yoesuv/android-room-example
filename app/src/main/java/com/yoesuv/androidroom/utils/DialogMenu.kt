package com.yoesuv.androidroom.utils

import android.content.Context
import android.view.LayoutInflater
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.yoesuv.androidroom.databinding.PopupMenuBinding

fun dialogMenu(context: Context, onEdit:() -> Unit, onDelete:() -> Unit) {

    val binding = PopupMenuBinding.inflate(LayoutInflater.from(context))
    val dialog = MaterialDialog(context).customView(view = binding.root, noVerticalPadding = true, horizontalPadding = false)
    binding.tvEditTask.setOnClickListener {
        dialog.dismiss()
        onEdit()
    }
    binding.tvDeleteTask.setOnClickListener {
        dialog.dismiss()
        onDelete()
    }
    dialog.show()

}