package com.yoesuv.androidroom.menu.task.views

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.databinding.ActivityMainBinding
import com.yoesuv.androidroom.databinding.PopupMenuBinding
import com.yoesuv.androidroom.menu.task.adapters.ListTaskAdapter
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import com.yoesuv.androidroom.menu.task.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: ListTaskAdapter
    private var listTask: MutableList<MyTaskModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

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
            it?.let {
                onListDataChange(it.toMutableList())
            }
        })
    }

    private fun setupRecycler(){
        binding.recyclerViewMain.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapter = ListTaskAdapter(listTask) {
            showDialogMenu()
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

    private fun showDialogMenu() {
        val inflater = LayoutInflater.from(this)
        val binding = PopupMenuBinding.inflate(inflater)
        val dialog = AlertDialog.Builder(this).create()
        dialog.setView(binding.root)
        dialog.show()
    }

}
