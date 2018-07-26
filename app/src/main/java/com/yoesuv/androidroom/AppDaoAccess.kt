package com.yoesuv.androidroom

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import com.yoesuv.androidroom.menu.task.rooms.MyTask

@Dao
interface AppDaoAccess {

    @Insert
    fun insertTask(myTask: MyTask)
}