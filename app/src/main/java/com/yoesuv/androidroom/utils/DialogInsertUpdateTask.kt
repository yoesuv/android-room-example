package com.yoesuv.androidroom.utils

import androidx.appcompat.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.databinding.PopupInsertUpdateTaskBinding
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import com.yoesuv.androidroom.menu.task.viewmodels.PopupInsertUpdateTaskViewModel

fun dialogInsertUpdateTask(context: Context, myTaskModel: MyTaskModel?, data:(title: String?, content: String?) -> Unit) {
    var dialogTask: AlertDialog? = null
    val dialogBuilder: MaterialAlertDialogBuilder?
    val binding = PopupInsertUpdateTaskBinding.inflate(LayoutInflater.from(context))
    val viewModel = PopupInsertUpdateTaskViewModel { title, content ->
        data(title, content)
        dialogTask?.dismiss()
    }
    binding.popup = viewModel
    viewModel.setupData(context, myTaskModel)
    binding.buttonCancel.setOnClickListener {
        dialogTask?.dismiss()
    }
    dialogBuilder = MaterialAlertDialogBuilder(context, R.style.DialogTheme)
        .setView(binding.root)
    dialogTask = dialogBuilder.show()
    dialogTask.setCancelable(false)
    dialogTask.setCanceledOnTouchOutside(false)
}