package com.yoesuv.androidroom.menu.task.viewmodels

import android.app.Activity
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import android.os.AsyncTask
import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.yoesuv.androidroom.db.TaskDatabase
import com.yoesuv.androidroom.data.AppConstant
import com.yoesuv.androidroom.menu.task.AdapterOnClickListener
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import com.yoesuv.androidroom.utils.dialogInsertUpdateTask

class MainViewModel(application: Application): AndroidViewModel(application) {

    private var taskDatabase: TaskDatabase = Room.databaseBuilder(application.applicationContext, TaskDatabase::class.java, AppConstant.DATABASE_NAME).build()

    var listTask: MutableLiveData<MutableList<MyTaskModel>> = MutableLiveData()

    fun showAllTask(){
        val listTaskRoom: List<MyTaskModel> = DatabaseAsyncSelectAll(taskDatabase).execute().get()
        val lisTaskAll: MutableList<MyTaskModel> = mutableListOf()
        for(i:Int in 0 until(listTaskRoom.size)){
                lisTaskAll.add(MyTaskModel(listTaskRoom[i].idTask, listTaskRoom[i].titleTask, listTaskRoom[i].contentTask))
        }
        this.listTask.value = lisTaskAll
    }

    fun showInsertTask(view: View){
        dialogInsertUpdateTask(view.context, null)
    }

    fun showUpdateTask(activity: Activity, myTask: MyTaskModel, adapterOnClickListener: AdapterOnClickListener){
        dialogInsertUpdateTask(activity, myTask)
    }

    fun deleteTask(myTask: MyTaskModel){
        val myTaskRoom = DatabaseAsyncGetTask(taskDatabase, myTask.idTask).execute().get()
        DatabaseAsyncDelete(taskDatabase, myTaskRoom).execute()
    }

    /**
     * select all from data task
     */
    class DatabaseAsyncSelectAll(private val taskDatabase: TaskDatabase): AsyncTask<Void, Void, List<MyTaskModel>>(){

        override fun doInBackground(vararg p0: Void?): List<MyTaskModel> {
            return taskDatabase.tasksDaoAccess().selectAll()
        }
    }

    /**
     * get task
     */
    class DatabaseAsyncGetTask(private val taskDatabase: TaskDatabase, val id:Int): AsyncTask<Void, Void, MyTaskModel>(){
        override fun doInBackground(vararg p0: Void?): MyTaskModel {
            return taskDatabase.tasksDaoAccess().getTask(id)
        }
    }

    /**
     * delete data task
     */
    class DatabaseAsyncDelete(private val taskDatabase: TaskDatabase, private val myTaskRoom: MyTaskModel): AsyncTask<Void, Void, List<MyTaskModel>>(){
        override fun doInBackground(vararg p0: Void?): List<MyTaskModel>? {
            taskDatabase.tasksDaoAccess().delete(myTaskRoom)
            return null
        }
    }

}