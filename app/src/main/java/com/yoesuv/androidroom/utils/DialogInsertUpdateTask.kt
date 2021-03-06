package com.yoesuv.androidroom.utils

import android.content.Context
import android.view.LayoutInflater
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.yoesuv.androidroom.databinding.PopupInsertUpdateTaskBinding
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import com.yoesuv.androidroom.menu.task.viewmodels.PopupInsertUpdateTaskViewModel

fun dialogInsertUpdateTask(context: Context, myTaskModel: MyTaskModel?, data:(title: String?, content: String?) -> Unit) {

    val binding = PopupInsertUpdateTaskBinding.inflate(LayoutInflater.from(context))
    val dialog = MaterialDialog(context).customView(view = binding.root, noVerticalPadding = true, horizontalPadding = false)
    val viewModel = PopupInsertUpdateTaskViewModel { title, content ->
        data(title, content)
        dialog.dismiss()
    }
    binding.popup = viewModel
    viewModel.setupData(context, myTaskModel)

    binding.buttonCancel.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()

}