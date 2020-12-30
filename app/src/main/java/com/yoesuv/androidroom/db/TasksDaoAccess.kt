package com.yoesuv.androidroom.db

import androidx.room.*
import com.yoesuv.androidroom.menu.task.models.MyTaskModel

@Dao
interface TasksDaoAccess {

    @Insert
    suspend fun insertTask(myTaskModel: MyTaskModel)

    @Query("SELECT * FROM my_tasks")
    suspend fun selectAll(): List<MyTaskModel>

    @Query("SELECT * FROM my_tasks WHERE idTask=:idTask")
    fun getTask(idTask: Int): MyTaskModel

    @Update
    fun updateTask(myTaskModel: MyTaskModel)

    @Delete
    fun delete(myTaskModel: MyTaskModel)

    @Query("DELETE FROM my_tasks")
    fun deleteAll()

}