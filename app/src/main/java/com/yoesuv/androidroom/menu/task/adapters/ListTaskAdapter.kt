package com.yoesuv.androidroom.menu.task.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.databinding.ItemTaskBinding
import com.yoesuv.androidroom.menu.task.models.MyTask
import com.yoesuv.androidroom.menu.task.viewmodels.ItemTaskViewModel

class ListTaskAdapter(context: Context, var listTask: MutableList<MyTask>?): RecyclerView.Adapter<ListTaskAdapter.TaskViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding: ItemTaskBinding = DataBindingUtil.inflate(inflater, R.layout.item_task, parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listTask?.size!!
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.setupItem(listTask!![holder.adapterPosition])
    }

    class TaskViewHolder(private val binding: ItemTaskBinding?) : RecyclerView.ViewHolder(binding?.root) {
        fun setupItem(myTask: MyTask){
            binding?.itemTask = ItemTaskViewModel(myTask)
        }
    }
}