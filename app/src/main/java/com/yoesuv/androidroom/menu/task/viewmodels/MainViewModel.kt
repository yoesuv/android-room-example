package com.yoesuv.androidroom.menu.task.viewmodels

import android.app.Activity
import android.arch.persistence.room.Room
import android.os.AsyncTask
import com.yoesuv.androidroom.AppDatabase
import com.yoesuv.androidroom.data.AppConstant
import com.yoesuv.androidroom.menu.task.rooms.MyTask

class MainViewModel(activity: Activity) {

    private var appDatabase:AppDatabase? = Room.databaseBuilder(activity, AppDatabase::class.java, AppConstant.DATABASE_NAME).build()

    fun insertTask(){
        val myTask = MyTask()
        myTask.task = "Bayar Hutang"
        DatabaseAsync(myTask, appDatabase).execute()

    }

    class DatabaseAsync(private val myTask: MyTask, private val appDatabase: AppDatabase?): AsyncTask<Void, Void, Void>(){

        override fun doInBackground(vararg p0: Void?): Void? {
            appDatabase?.appDaoAccess()!!.insertTask(myTask)
            return null
        }

    }

}