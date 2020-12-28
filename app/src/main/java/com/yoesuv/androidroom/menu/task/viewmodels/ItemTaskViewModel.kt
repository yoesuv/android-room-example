package com.yoesuv.androidroom.menu.task.viewmodels

import androidx.databinding.ObservableField
import android.view.View
import com.yoesuv.androidroom.menu.task.AdapterOnClickListener
import com.yoesuv.androidroom.menu.task.models.MyTask

class ItemTaskViewModel(private val myTask: MyTask, private val adapterOnClickListener: AdapterOnClickListener, private val position: Int) {

    var title: ObservableField<String?> = ObservableField(myTask.titleTask)
    var content: ObservableField<String?> = ObservableField(myTask.contentTask)

    fun clickDelete(view: View){
        adapterOnClickListener.onItemAdapterClickedDelete(myTask, position)
    }

    fun clickEdit(view: View){
        adapterOnClickListener.onItemAdapterClickedEdit(myTask)
    }
}