package com.yoesuv.androidroom.db

import android.content.Context
import com.yoesuv.androidroom.menu.task.models.MyTaskModel

class DbTasksRepository(context: Context) {

    private val db = TaskDatabase.getInstance(context)?.tasksDaoAccess()

    suspend fun insertTask(myTaskModel: MyTaskModel) {
        db?.insertTask(myTaskModel)
    }

    suspend fun selectAll(): List<MyTaskModel>? {
        return db?.selectAll()
    }

    suspend fun updateTask(myTaskModel: MyTaskModel) {
        db?.updateTask(myTaskModel)
    }

    suspend fun deleteTask(idTask: Int) {
        db?.deleteTaskById(idTask)
    }


}