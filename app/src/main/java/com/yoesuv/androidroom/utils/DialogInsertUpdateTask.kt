package com.yoesuv.androidroom.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.yoesuv.androidroom.databinding.PopupInsertUpdateTaskBinding
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import com.yoesuv.androidroom.menu.task.viewmodels.PopupInsertUpdateTaskViewModel

fun dialogInsertUpdateTask(context: Context, myTaskModel: MyTaskModel?, data:(title: String?, content: String?) -> Unit) {
    var dialogTask: AlertDialog? = null
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
    dialogTask = AlertDialog.Builder(context)
        .setView(binding.root)
        .create()
    dialogTask?.setCancelable(false)
    dialogTask?.setCanceledOnTouchOutside(false)
    dialogTask?.show()
}