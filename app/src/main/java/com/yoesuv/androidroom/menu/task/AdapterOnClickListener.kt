package com.yoesuv.androidroom.menu.task

import com.yoesuv.androidroom.menu.task.models.MyTaskModel

interface AdapterOnClickListener {
    fun onItemAdapterClickedEdit(myTask: MyTaskModel)
    fun onItemAdapterClickedDelete(myTask: MyTaskModel, position: Int)
    fun onUpdateCallback()
}