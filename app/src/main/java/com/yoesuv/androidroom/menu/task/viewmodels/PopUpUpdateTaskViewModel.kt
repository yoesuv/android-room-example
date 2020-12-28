package com.yoesuv.androidroom.menu.task.viewmodels

import android.app.Dialog
import androidx.databinding.ObservableField
import android.os.AsyncTask
import android.view.View
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.menu.task.AdapterOnClickListener
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import com.yoesuv.androidroom.db.TaskDatabase

class PopUpUpdateTaskViewModel(private val dialog: Dialog, private val myTask: MyTaskModel, private val taskDatabase: TaskDatabase, private val mainViewModel: MainViewModel, private val iface: AdapterOnClickListener) {

    var title: ObservableField<String> = ObservableField(myTask.titleTask!!)
    var content: ObservableField<String> = ObservableField(myTask.contentTask!!)
    var titleError: ObservableField<String> = ObservableField()
    var contentError: ObservableField<String> = ObservableField()

    fun clickCancel(view: View){
        dialog.dismiss()
    }

    fun clickUpdate(view: View){
        titleError.set(null)
        contentError.set(null)
        when {
            title.get()?.trim().equals("") -> {
                titleError.set(dialog.context.getString(R.string.error_input_title_empty))
            }
            content.get()?.trim().equals("") -> {
                contentError.set(dialog.context.getString(R.string.error_input_content_empty))
            }
            else -> {
                dialog.dismiss()
                val myTaskModel = DatabaseAsyncGetTask(taskDatabase, myTask.idTask).execute().get()
                myTaskModel.titleTask = title.get()
                myTaskModel.contentTask = content.get()
                DatabaseAsyncUpdateTask(taskDatabase, myTaskModel, mainViewModel).execute()
                iface.onUpdateCallback()
            }
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
     * update task
     */
    class DatabaseAsyncUpdateTask(private val taskDatabase: TaskDatabase, private val myTaskRoom: MyTaskModel, private val mainViewModel: MainViewModel): AsyncTask<Void, Void, Void>(){

        override fun doInBackground(vararg p0: Void?): Void? {
            taskDatabase.tasksDaoAccess().updateTask(myTaskRoom)
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            mainViewModel.showAllTask()
        }
    }
}