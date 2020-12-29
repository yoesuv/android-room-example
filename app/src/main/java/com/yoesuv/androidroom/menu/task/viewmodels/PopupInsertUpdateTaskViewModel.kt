package com.yoesuv.androidroom.menu.task.viewmodels

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.menu.task.models.MyTaskModel

class PopupInsertUpdateTaskViewModel(private val onApply:(title: String?, content: String?) -> Unit): ViewModel() {

    var popupTitle: ObservableField<String> = ObservableField()
    var buttonAction: ObservableField<String> = ObservableField()
    var title: ObservableField<String> = ObservableField()
    var content: ObservableField<String> = ObservableField()

    var titleError: ObservableField<String> = ObservableField()
    var contentError: ObservableField<String> = ObservableField()

    fun setupData(context: Context, myTaskModel: MyTaskModel?) {
        if (myTaskModel == null) {
            popupTitle.set(context.getString(R.string.insert_new_task))
            buttonAction.set(context.getString(R.string.button_new_task_save))
        } else {
            popupTitle.set(context.getString(R.string.update_task))
            buttonAction.set(context.getString(R.string.button_task_update))
            title.set(myTaskModel.titleTask)
            content.set(myTaskModel.contentTask)
        }
    }

    fun clickApply(view: View){
        titleError.set(null)
        contentError.set(null)
        if (title.get().isNullOrEmpty()) {
            titleError.set(view.context.getString(R.string.error_input_title_empty))
        } else if (content.get().isNullOrEmpty()) {
            contentError.set(view.context.getString(R.string.error_input_content_empty))
        } else {
            onApply(title.get(), content.get())
        }
    }

}