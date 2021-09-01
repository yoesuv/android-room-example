package com.yoesuv.androidroom.menu.task.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.yoesuv.androidroom.db.DbTasksRepository
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import com.yoesuv.androidroom.utils.dialogInsertUpdateTask
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val dbTasks = DbTasksRepository(application.applicationContext)

    var listTask: MutableLiveData<List<MyTaskModel>> = MutableLiveData()

    fun showAllTask(){
        viewModelScope.launch {
            listTask.postValue(dbTasks.selectAll())
        }
    }

    fun showInsertTask(view: View){
        dialogInsertUpdateTask(view.context, null) { title, content ->
            viewModelScope.launch {
                dbTasks.insertTask(MyTaskModel(titleTask = title, contentTask = content))
                listTask.postValue(dbTasks.selectAll())
            }
        }
    }

    fun updateTask(myTaskModel: MyTaskModel?) {
        viewModelScope.launch {
            myTaskModel?.let {
                dbTasks.updateTask(it)
            }
        }
    }

    fun deleteTask(myTaskModel: MyTaskModel?) {
        viewModelScope.launch {
            myTaskModel?.idTask?.let {
                dbTasks.deleteTask(it)
            }
        }
    }

}