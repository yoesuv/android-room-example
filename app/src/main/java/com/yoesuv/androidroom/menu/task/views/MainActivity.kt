package com.yoesuv.androidroom.menu.task.views

import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.databinding.ActivityMainBinding
import com.yoesuv.androidroom.menu.task.adapters.ListTaskAdapter
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import com.yoesuv.androidroom.menu.task.viewmodels.MainViewModel
import com.yoesuv.androidroom.utils.dialogInsertUpdateTask
import com.yoesuv.androidroom.utils.dialogMenu

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: ListTaskAdapter
    private var listTask: MutableList<MyTaskModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupToolbar()

        viewModel.showAllTask()

        setupRecycler()
    }

    private fun setupToolbar(){
        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.title = getString(R.string.my_task)
    }

    private fun setupBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.main = viewModel

        viewModel.listTask.observe(this, {
            it?.let {
                onListDataChange(it.toMutableList())
            }
        })
    }

    private fun setupRecycler(){
        binding.recyclerViewMain.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapter = ListTaskAdapter(listTask) { task ->
            dialogMenu(this, {
                showDialogEdit(task)
            },{
                viewModel.deleteTask(task)
            })
        }
        binding.recyclerViewMain.adapter = adapter
    }

    private fun onListDataChange(listTask: MutableList<MyTaskModel>){
        if(listTask.isNotEmpty()) {
            this.listTask.clear()
            for (i: Int in 0 until (listTask.size)) {
                this.listTask.add(listTask[i])
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun showDialogEdit(data: MyTaskModel?) {
        dialogInsertUpdateTask(this, data) { title, content ->
            data?.titleTask = title
            data?.contentTask = content
            viewModel.updateTask(data)
        }
    }

}
