package com.yoesuv.androidroom.menu.task.viewmodels

import android.app.Activity
import android.app.Dialog
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.databinding.DataBindingUtil
import android.graphics.Point
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.menu.task.rooms.TaskDatabase
import com.yoesuv.androidroom.data.AppConstant
import com.yoesuv.androidroom.databinding.PopupInsertTaskBinding
import com.yoesuv.androidroom.menu.task.models.MyTask
import com.yoesuv.androidroom.menu.task.rooms.MyTaskRoom
import com.yoesuv.androidroom.utils.Utility

class MainViewModel(val activity: Activity) {

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

    fun showInsertTask(view: View){
        val dialog = Dialog(activity)
        val popupBinding: PopupInsertTaskBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.popup_insert_task, null, false)
        popupBinding.popUpInsertTask = PopupInsertTaskViewModel(dialog)
        dialog.setContentView(popupBinding.root)
        val size:Point = Utility.getScreenSize(activity)
        val width = size.x
        val widthReduce = (width * 10) /100
        val widthShow = width - widthReduce
        Log.d(AppConstant.TAG_DEBUG,"MainViewModel $width - $widthReduce = $widthShow")
        dialog.window.setLayout(widthShow, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.show()
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