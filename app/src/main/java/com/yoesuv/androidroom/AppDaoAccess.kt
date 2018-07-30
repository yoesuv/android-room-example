package com.yoesuv.androidroom

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.yoesuv.androidroom.menu.task.models.MyTask
import com.yoesuv.androidroom.menu.task.rooms.MyTaskRoom

@Dao
interface AppDaoAccess {

    @Insert
    fun insertTask(myTaskRoom: MyTaskRoom)

    @Query("SELECT * FROM MyTaskRoom")
    fun selectAll(): List<MyTaskRoom>

    @Query("SELECT * FROM MyTaskRoom WHERE idTask=:idTask")
    fun getTask(idTask: Int): MyTaskRoom

    @Delete
    fun delete(myTaskRoom: MyTaskRoom)

}