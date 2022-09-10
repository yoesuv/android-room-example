package com.yoesuv.androidroom.db

import androidx.room.*
import com.yoesuv.androidroom.menu.task.models.MyTaskModel

@Dao
interface TasksDaoAccess {

    @Insert
    suspend fun insertTask(myTaskModel: MyTaskModel)

    @Query("SELECT * FROM my_tasks")
    suspend fun selectAll(): List<MyTaskModel>

    @Update
    suspend fun updateTask(myTaskModel: MyTaskModel)

    @Query("DELETE FROM my_tasks WHERE idTask=:idTask")
    suspend fun deleteTaskById(idTask: Int)

    @Query("DELETE FROM my_tasks")
    suspend fun deleteAll()

}