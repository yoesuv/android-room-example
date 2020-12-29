package com.yoesuv.androidroom.utils

import android.content.Context
import android.view.LayoutInflater
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.yoesuv.androidroom.databinding.PopupInsertUpdateTaskBinding

fun dialogInsertUpdateTask(context: Context) {

    val binding = PopupInsertUpdateTaskBinding.inflate(LayoutInflater.from(context))
    val dialog = MaterialDialog(context).customView(view = binding.root, noVerticalPadding = true, horizontalPadding = false)

    binding.buttonCancel.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()

}