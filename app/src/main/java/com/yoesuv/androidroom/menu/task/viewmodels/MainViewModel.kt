package com.yoesuv.androidroom.menu.task.viewmodels

import android.app.Activity
import android.arch.persistence.room.Room
import android.os.AsyncTask
import android.util.Log
import com.yoesuv.androidroom.menu.task.rooms.TaskDatabase
import com.yoesuv.androidroom.data.AppConstant
import com.yoesuv.androidroom.menu.task.rooms.MyTask

class MainViewModel(activity: Activity) {

    private var taskDatabase: TaskDatabase = Room.databaseBuilder(activity, TaskDatabase::class.java, AppConstant.DATABASE_NAME).build()

    fun insertTask(){
        val myTask = MyTask("Bayar Hutang")
        DatabaseAsync(myTask, taskDatabase).execute()

        DatabaseAsyncSelectAll(taskDatabase).execute()
    }

    /**
     * insert data task
     */
    class DatabaseAsync(private val myTask: MyTask, private val taskDatabase: TaskDatabase): AsyncTask<Void, Void, Void>(){

        override fun doInBackground(vararg p0: Void?): Void? {
            taskDatabase.appDaoAccess().insertTask(myTask)
            return null
        }

    }

    /**
     * select all from data task
     */
    class DatabaseAsyncSelectAll(private val taskDatabase: TaskDatabase): AsyncTask<Void, Void, List<MyTask>>(){

        override fun doInBackground(vararg p0: Void?): List<MyTask> {
            return taskDatabase.appDaoAccess().selectAll()
        }

        override fun onPostExecute(result: List<MyTask>?) {
            super.onPostExecute(result)
            Log.d(AppConstant.TAG_DEBUG,"DatabaseAsyncSelectAll # jumlah data : ${result?.size}")
        }
    }

}