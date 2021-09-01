package com.yoesuv.androidroom.menu.task.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yoesuv.androidroom.db.DbTasksRepository
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import com.yoesuv.androidroom.utils.dialogInsertUpdateTask

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val dbTasks = DbTasksRepository(application.applicationContext, viewModelScope)

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

    fun updateTask(myTaskModel: MyTaskModel?) {
        myTaskModel?.let {
            dbTasks.updateTask(it) {
                showAllTask()
            }
        }
    }

    fun deleteTask(myTaskModel: MyTaskModel?) {
        myTaskModel?.idTask?.let {
            dbTasks.deleteTask(it) {
                showAllTask()
            }
        }
    }

}