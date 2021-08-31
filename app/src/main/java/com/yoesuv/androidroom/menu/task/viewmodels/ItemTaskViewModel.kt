package com.yoesuv.androidroom.menu.task.viewmodels

import androidx.databinding.ObservableField
import com.yoesuv.androidroom.menu.task.models.MyTaskModel

class ItemTaskViewModel(myTask: MyTaskModel?) {
    var title: ObservableField<String?> = ObservableField(myTask?.titleTask)
    var content: ObservableField<String?> = ObservableField(myTask?.contentTask)
}