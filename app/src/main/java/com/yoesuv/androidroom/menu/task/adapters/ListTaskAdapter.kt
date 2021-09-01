package com.yoesuv.androidroom.menu.task.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.yoesuv.androidroom.menu.task.models.MyTaskModel

class ListTaskAdapter(private var listTask: MutableList<MyTaskModel>?, val onItemClick:(Int, MyTaskModel?) -> Unit): RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return listTask?.size!!
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(listTask?.get(position)) {
            onItemClick(position, it)
        }
    }
}