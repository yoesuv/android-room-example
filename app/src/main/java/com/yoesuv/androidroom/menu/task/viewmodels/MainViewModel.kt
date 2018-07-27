package com.yoesuv.androidroom.menu.task.viewmodels

import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.os.AsyncTask
import com.yoesuv.androidroom.menu.task.rooms.TaskDatabase
import com.yoesuv.androidroom.data.AppConstant
import com.yoesuv.androidroom.menu.task.models.MyTask
import com.yoesuv.androidroom.menu.task.rooms.MyTaskRoom

class MainViewModel(activity: Activity) {

    private var taskDatabase: TaskDatabase = Room.databaseBuilder(activity, TaskDatabase::class.java, AppConstant.DATABASE_NAME).build()

    var listTask: MutableLiveData<MutableList<MyTask>> = MutableLiveData()

    fun insertTask(){
        val myTask = MyTaskRoom("Bayar Hutang","Hutangnya Negara 2000 Trilliun")
        DatabaseAsync(myTask, taskDatabase).execute()
    }

    fun showAllTask(){
        val listTaskRoom: List<MyTaskRoom> = DatabaseAsyncSelectAll(taskDatabase).execute().get()
        val lisTaskAll: MutableList<MyTask> = mutableListOf()
        for(i:Int in 0 until(listTaskRoom.size)){
                lisTaskAll.add(MyTask(listTaskRoom[i].idTask.toString(), listTaskRoom[i].task, listTaskRoom[i].content))
        }
        this.listTask.value = lisTaskAll
    }

    /**
     * insert data task
     */
    class DatabaseAsync(private val myTaskRoom: MyTaskRoom, private val taskDatabase: TaskDatabase): AsyncTask<Void, Void, Void>(){

        override fun doInBackground(vararg p0: Void?): Void? {
            taskDatabase.appDaoAccess().insertTask(myTaskRoom)
            return null
        }

    }

    /**
     * select all from data task
     */
    class DatabaseAsyncSelectAll(private val taskDatabase: TaskDatabase): AsyncTask<Void, Void, List<MyTaskRoom>>(){

        override fun doInBackground(vararg p0: Void?): List<MyTaskRoom> {
            return taskDatabase.appDaoAccess().selectAll()
        }
    }

}