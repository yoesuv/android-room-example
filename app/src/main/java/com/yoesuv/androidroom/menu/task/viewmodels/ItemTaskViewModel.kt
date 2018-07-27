package com.yoesuv.androidroom.menu.task.viewmodels

import android.databinding.ObservableField
import com.yoesuv.androidroom.menu.task.models.MyTask

class ItemTaskViewModel(myTask: MyTask) {
    var title: ObservableField<String?> = ObservableField(myTask.titleTask)
    var content: ObservableField<String?> = ObservableField(myTask.contentTask)
}