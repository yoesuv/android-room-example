package com.yoesuv.androidroom.menu.task.viewmodels

import android.app.Dialog
import android.databinding.ObservableField
import android.os.AsyncTask
import android.view.View
import com.yoesuv.androidroom.menu.task.AdapterOnClickListener
import com.yoesuv.androidroom.menu.task.models.MyTask
import com.yoesuv.androidroom.menu.task.rooms.MyTaskRoom
import com.yoesuv.androidroom.menu.task.rooms.TaskDatabase

class PopUpUpdateTaskViewModel(private val dialog: Dialog, private val myTask: MyTask, private val taskDatabase: TaskDatabase, private val mainViewModel: MainViewModel, private val iface: AdapterOnClickListener) {

    var title: ObservableField<String> = ObservableField(myTask.titleTask!!)
    var content: ObservableField<String> = ObservableField(myTask.contentTask!!)


    fun clickCancel(view: View){
        dialog.dismiss()
    }

    fun clickUpdate(view: View){
        dialog.dismiss()
        val myTaskRoom = DatabaseAsyncGetTask(taskDatabase, myTask.idTask!!).execute().get()
        myTaskRoom.task =  title.get()
        myTaskRoom.content = content.get()
        DatabaseAsyncUpdateTask(taskDatabase, myTaskRoom, mainViewModel).execute()
        iface.onUpdateCallback()
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
     * update task
     */
    class DatabaseAsyncUpdateTask(private val taskDatabase: TaskDatabase, private val myTaskRoom: MyTaskRoom, private val mainViewModel: MainViewModel): AsyncTask<Void, Void, Void>(){

        override fun doInBackground(vararg p0: Void?): Void? {
            taskDatabase.appDaoAccess().updateTask(myTaskRoom)
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            mainViewModel.showAllTask()
        }
    }
}