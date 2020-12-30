package com.yoesuv.androidroom.db

import android.content.Context
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DbTasksRepository(context: Context, private val scope: CoroutineScope) {

    private val db = TaskDatabase.getInstance(context)?.tasksDaoAccess()

    fun selectAll(tasks:(List<MyTaskModel>?) -> Unit) {
        scope.launch {
            tasks(db?.selectAll())
        }
    }


}