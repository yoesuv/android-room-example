package com.yoesuv.androidroom.menu.task.viewmodels

import android.app.Activity
import android.app.Application
import android.app.Dialog
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.databinding.DataBindingUtil
import android.graphics.Point
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.db.TaskDatabase
import com.yoesuv.androidroom.data.AppConstant
import com.yoesuv.androidroom.databinding.PopupUpdateTaskBinding
import com.yoesuv.androidroom.menu.task.AdapterOnClickListener
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import com.yoesuv.androidroom.utils.Utility
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
        dialogInsertUpdateTask(view.context)
    }

    fun showUpdateTask(activity: Activity, myTask: MyTaskModel, adapterOnClickListener: AdapterOnClickListener){
        val dialog = Dialog(activity)
        val popUpBinding: PopupUpdateTaskBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.popup_update_task, null, false)
        popUpBinding.popUpUpdateTask = PopUpUpdateTaskViewModel(dialog, myTask, taskDatabase, this, adapterOnClickListener)
        dialog.setContentView(popUpBinding.root)
        val size:Point = Utility.getScreenSize(activity)
        val width = size.x
        val widthReduce = (width * 10) /100
        val widthShow = width - widthReduce
        dialog.window?.setLayout(widthShow, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
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