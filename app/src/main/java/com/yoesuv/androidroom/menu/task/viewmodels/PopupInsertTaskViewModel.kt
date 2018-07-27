package com.yoesuv.androidroom.menu.task.viewmodels

import android.app.Dialog
import android.databinding.ObservableField
import android.os.AsyncTask
import android.view.View
import com.yoesuv.androidroom.menu.task.rooms.MyTaskRoom
import com.yoesuv.androidroom.menu.task.rooms.TaskDatabase

class PopupInsertTaskViewModel(private val dialog: Dialog, private val taskDatabase: TaskDatabase, private val mainViewModel: MainViewModel) {

    var title: ObservableField<String> = ObservableField()
    var content: ObservableField<String> = ObservableField()

    fun clickCancel(view: View){
        dialog.dismiss()
    }

    fun clickSave(view: View){
        val myTask = MyTaskRoom(title.get(), content.get())
        DatabaseAsync(myTask, taskDatabase, mainViewModel).execute()
        dialog.dismiss()
    }

    /**
     * insert data task
     */
    class DatabaseAsync(private val myTaskRoom: MyTaskRoom, private val taskDatabase: TaskDatabase, private val mainViewModel: MainViewModel): AsyncTask<Void, Void, Void>(){

        override fun doInBackground(vararg p0: Void?): Void? {
            taskDatabase.appDaoAccess().insertTask(myTaskRoom)
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            mainViewModel.showAllTask()
        }

    }
}