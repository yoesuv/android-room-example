package com.yoesuv.androidroom.menu.task.rooms

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.yoesuv.androidroom.AppDaoAccess

@Database(entities = [(MyTaskRoom::class)], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun appDaoAccess(): AppDaoAccess
}
