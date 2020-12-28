package com.yoesuv.androidroom.menu.task.viewmodels

import android.app.Dialog
import androidx.databinding.ObservableField
import android.os.AsyncTask
import android.view.View
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import com.yoesuv.androidroom.menu.task.rooms.TaskDatabase

class PopupInsertTaskViewModel(private val dialog: Dialog, private val taskDatabase: TaskDatabase, private val mainViewModel: MainViewModel) {

    var title: ObservableField<String> = ObservableField()
    var content: ObservableField<String> = ObservableField()

    var titleError: ObservableField<String> = ObservableField()
    var contentError: ObservableField<String> = ObservableField()

    fun clickCancel(view: View){
        dialog.dismiss()
    }

    fun clickSave(view: View){
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
                val myTask = MyTaskModel(titleTask =  title.get(), contentTask = content.get())
                DatabaseAsync(myTask, taskDatabase, mainViewModel).execute()
                dialog.dismiss()
            }
        }
    }

    /**
     * insert data task
     */
    class DatabaseAsync(private val myTaskRoom: MyTaskModel, private val taskDatabase: TaskDatabase, private val mainViewModel: MainViewModel): AsyncTask<Void, Void, Void>(){

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