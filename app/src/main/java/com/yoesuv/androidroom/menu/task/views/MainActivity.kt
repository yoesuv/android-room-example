package com.yoesuv.androidroom.menu.task.views

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.databinding.ActivityMainBinding
import com.yoesuv.androidroom.menu.task.AdapterOnClickListener
import com.yoesuv.androidroom.menu.task.adapters.ListTaskAdapter
import com.yoesuv.androidroom.menu.task.models.MyTask
import com.yoesuv.androidroom.menu.task.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), AdapterOnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: ListTaskAdapter
    private var listTask: MutableList<MyTask> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = MainViewModel(this)

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
        binding.main = viewModel

        viewModel.listTask.observe(this, Observer {
            onListDataChange(it!!)
        })
    }

    private fun setupRecycler(){
        val lManager = LinearLayoutManager(this)
        binding.recyclerViewMain.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.recyclerViewMain.layoutManager = lManager

        adapter = ListTaskAdapter(this, listTask, this)
        binding.recyclerViewMain.adapter = adapter
    }

    private fun onListDataChange(listTask: MutableList<MyTask>){
        if(listTask.isNotEmpty()) {
            this.listTask.clear()
            for (i: Int in 0 until (listTask.size)) {
                this.listTask.add(listTask[i])
            }
            adapter.notifyDataSetChanged()
        }
    }

    override fun onItemAdapterClickedEdit(myTask: MyTask) {
        viewModel.showUpdateTask(myTask, this)
    }

    override fun onItemAdapterClickedDelete(myTask: MyTask, position: Int) {
        viewModel.deleteTask(myTask)
        adapter.removeItem(binding.recyclerViewMain, position)
    }

    override fun onUpdateCallback() {
        adapter.updateItem(binding.recyclerViewMain)
    }
}
