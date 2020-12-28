package com.yoesuv.androidroom.menu.task.rooms

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yoesuv.androidroom.AppDaoAccess

@Database(entities = [(MyTaskRoom::class)], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun appDaoAccess(): AppDaoAccess
}
