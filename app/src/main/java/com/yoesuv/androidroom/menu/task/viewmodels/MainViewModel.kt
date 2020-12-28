package com.yoesuv.androidroom.menu.task.viewmodels

import android.app.Activity
import android.app.Dialog
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.databinding.DataBindingUtil
import android.graphics.Point
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.menu.task.rooms.TaskDatabase
import com.yoesuv.androidroom.data.AppConstant
import com.yoesuv.androidroom.databinding.PopupInsertTaskBinding
import com.yoesuv.androidroom.databinding.PopupUpdateTaskBinding
import com.yoesuv.androidroom.menu.task.AdapterOnClickListener
import com.yoesuv.androidroom.menu.task.models.MyTask
import com.yoesuv.androidroom.menu.task.rooms.MyTaskRoom
import com.yoesuv.androidroom.utils.Utility

class MainViewModel(private val activity: Activity) {

    private var taskDatabase: TaskDatabase = Room.databaseBuilder(activity, TaskDatabase::class.java, AppConstant.DATABASE_NAME).build()

    var listTask: MutableLiveData<MutableList<MyTask>> = MutableLiveData()

    fun showAllTask(){
        val listTaskRoom: List<MyTaskRoom> = DatabaseAsyncSelectAll(taskDatabase).execute().get()
        val lisTaskAll: MutableList<MyTask> = mutableListOf()
        for(i:Int in 0 until(listTaskRoom.size)){
                lisTaskAll.add(MyTask(listTaskRoom[i].idTask, listTaskRoom[i].task, listTaskRoom[i].content))
        }
        this.listTask.value = lisTaskAll
    }

    fun showInsertTask(view: View){
        val dialog = Dialog(activity)
        val popupBinding: PopupInsertTaskBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.popup_insert_task, null, false)
        popupBinding.popUpInsertTask = PopupInsertTaskViewModel(dialog, taskDatabase, this)
        dialog.setContentView(popupBinding.root)
        val size:Point = Utility.getScreenSize(activity)
        val width = size.x
        val widthReduce = (width * 10) /100
        val widthShow = width - widthReduce
        dialog.window.setLayout(widthShow, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    fun showUpdateTask(myTask: MyTask, adapterOnClickListener: AdapterOnClickListener){
        val dialog = Dialog(activity)
        val popUpBinding: PopupUpdateTaskBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.popup_update_task, null, false)
        popUpBinding.popUpUpdateTask = PopUpUpdateTaskViewModel(dialog, myTask, taskDatabase, this, adapterOnClickListener)
        dialog.setContentView(popUpBinding.root)
        val size:Point = Utility.getScreenSize(activity)
        val width = size.x
        val widthReduce = (width * 10) /100
        val widthShow = width - widthReduce
        dialog.window.setLayout(widthShow, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    fun deleteTask(myTask: MyTask){
        val myTaskRoom = DatabaseAsyncGetTask(taskDatabase, myTask.idTask!!).execute().get()
        DatabaseAsyncDelete(taskDatabase, myTaskRoom).execute()
    }

    /**
     * select all from data task
     */
    class DatabaseAsyncSelectAll(private val taskDatabase: TaskDatabase): AsyncTask<Void, Void, List<MyTaskRoom>>(){

        override fun doInBackground(vararg p0: Void?): List<MyTaskRoom> {
            return taskDatabase.appDaoAccess().selectAll()
        }
    }

    /**
     * get task
     */
    class DatabaseAsyncGetTask(private val taskDatabase: TaskDatabase, val id:Int): AsyncTask<Void, Void, MyTaskRoom>(){
        override fun doInBackground(vararg p0: Void?): MyTaskRoom {
            return taskDatabase.appDaoAccess().getTask(id)
        }
    }

    /**
     * delete data task
     */
    class DatabaseAsyncDelete(private val taskDatabase: TaskDatabase, private val myTaskRoom: MyTaskRoom): AsyncTask<Void, Void, List<MyTaskRoom>>(){
        override fun doInBackground(vararg p0: Void?): List<MyTaskRoom>? {
            taskDatabase.appDaoAccess().delete(myTaskRoom)
            return null
        }
    }

}