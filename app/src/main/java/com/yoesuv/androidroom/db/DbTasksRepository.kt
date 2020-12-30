package com.yoesuv.androidroom.db

import android.content.Context
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DbTasksRepository(context: Context, private val scope: CoroutineScope) {

    private val db = TaskDatabase.getInstance(context)?.tasksDaoAccess()

    fun insertTask(myTaskModel: MyTaskModel, onDone:() -> Unit) {
        scope.launch {
            db?.insertTask(myTaskModel)
            onDone()
        }
    }

    fun selectAll(tasks:(List<MyTaskModel>?) -> Unit) {
        scope.launch {
            tasks(db?.selectAll())
        }
    }

    fun deleteTask(idTask: Int, onDone: () -> Unit) {
        scope.launch {
            db?.deleteTaskById(idTask)
            onDone()
        }
    }


}