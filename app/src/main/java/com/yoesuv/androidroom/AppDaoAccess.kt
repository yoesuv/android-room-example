package com.yoesuv.androidroom

import androidx.room.*
import com.yoesuv.androidroom.menu.task.models.MyTaskModel

@Dao
interface AppDaoAccess {

    @Insert
    fun insertTask(myTaskModel: MyTaskModel)

    @Query("SELECT * FROM my_tasks")
    fun selectAll(): List<MyTaskModel>

    @Query("SELECT * FROM my_tasks WHERE idTask=:idTask")
    fun getTask(idTask: Int): MyTaskModel

    @Update
    fun updateTask(myTaskModel: MyTaskModel)

    @Delete
    fun delete(myTaskModel: MyTaskModel)

}