package com.yoesuv.androidroom.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yoesuv.androidroom.data.AppConstant
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import com.yoesuv.androidroom.BuildConfig
import java.util.concurrent.Executors

@Database(entities = [MyTaskModel::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun tasksDaoAccess(): TasksDaoAccess

    companion object {

        @Volatile
        private var instance: TaskDatabase? = null

        @Synchronized
        fun getInstance(context: Context): TaskDatabase? {
            if (instance == null) {
                instance = create(context)
            }
            return instance
        }

        private fun create(context: Context): TaskDatabase {
            val dbBuilder =
                Room.databaseBuilder(context, TaskDatabase::class.java, AppConstant.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
            if (BuildConfig.DEBUG) {
                dbBuilder.setQueryCallback({ sqlQuery, bindArgs ->
                    Log.d(
                        "result_debug",
                        "TaskDatabase # room query $sqlQuery ====> args $bindArgs\""
                    )
                }, Executors.newSingleThreadExecutor())

            }
            return dbBuilder.build()
        }
    }

}
