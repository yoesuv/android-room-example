package com.yoesuv.androidroom.menu.task.viewmodels

import android.app.Activity
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import android.os.AsyncTask
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yoesuv.androidroom.db.TaskDatabase
import com.yoesuv.androidroom.data.AppConstant
import com.yoesuv.androidroom.db.DbTasksRepository
import com.yoesuv.androidroom.menu.task.AdapterOnClickListener
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import com.yoesuv.androidroom.utils.dialogInsertUpdateTask

class MainViewModel(application: Application): AndroidViewModel(application) {

    private var taskDatabase: TaskDatabase = Room.databaseBuilder(application.applicationContext, TaskDatabase::class.java, AppConstant.DATABASE_NAME).build()
    private val dbTasks =DbTasksRepository(application.applicationContext, viewModelScope)

    var listTask: MutableLiveData<List<MyTaskModel>> = MutableLiveData()

    fun showAllTask(){
        dbTasks.selectAll {
            this.listTask.postValue(it)
        }
    }

    fun showInsertTask(view: View){
        dialogInsertUpdateTask(view.context, null) { title, content ->
            dbTasks.insertTask(MyTaskModel(titleTask = title, contentTask = content)) {
                showAllTask()
            }
        }
    }

    fun showUpdateTask(activity: Activity, myTask: MyTaskModel, adapterOnClickListener: AdapterOnClickListener){
        dialogInsertUpdateTask(activity, myTask) { title, content ->

        }
    }

    fun deleteTask(myTask: MyTaskModel){
        val myTaskRoom = DatabaseAsyncGetTask(taskDatabase, myTask.idTask).execute().get()
        DatabaseAsyncDelete(taskDatabase, myTaskRoom).execute()
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