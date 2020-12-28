package com.yoesuv.androidroom.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yoesuv.androidroom.menu.task.models.MyTaskModel

@Database(entities = [(MyTaskModel::class)], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun tasksDaoAccess(): TasksDaoAccess
}
