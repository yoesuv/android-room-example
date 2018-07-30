package com.yoesuv.androidroom.menu.task

import com.yoesuv.androidroom.menu.task.models.MyTask

interface AdapterOnClickListener {
    fun onItemAdapterClickedEdit(myTask: MyTask)
    fun onItemAdapterClickedDelete(myTask: MyTask, position: Int)
}