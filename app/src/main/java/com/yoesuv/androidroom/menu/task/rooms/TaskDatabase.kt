package com.yoesuv.androidroom.menu.task.rooms

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yoesuv.androidroom.AppDaoAccess
import com.yoesuv.androidroom.menu.task.models.MyTaskModel

@Database(entities = [(MyTaskModel::class)], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun appDaoAccess(): AppDaoAccess
}
