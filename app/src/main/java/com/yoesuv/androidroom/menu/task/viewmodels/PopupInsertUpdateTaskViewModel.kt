package com.yoesuv.androidroom.menu.task.viewmodels

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.yoesuv.androidroom.R

class PopupInsertUpdateTaskViewModel(private val onApply:(title: String?, content: String?) -> Unit): ViewModel() {

    var title: ObservableField<String> = ObservableField()
    var content: ObservableField<String> = ObservableField()

    var titleError: ObservableField<String> = ObservableField()
    var contentError: ObservableField<String> = ObservableField()

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