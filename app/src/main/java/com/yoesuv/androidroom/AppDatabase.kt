package com.yoesuv.androidroom

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

import com.yoesuv.androidroom.menu.task.rooms.MyTask

@Database(entities = [(MyTask::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDaoAccess(): AppDaoAccess
}
