package com.yoesuv.androidroom.menu.task.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.yoesuv.androidroom.menu.task.models.MyTaskModel

class ListTaskAdapter(private var listTask: MutableList<MyTaskModel>?, val onItemClick:(MyTaskModel?) -> Unit): RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return listTask?.size!!
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(listTask?.get(position)) {
            onItemClick(it)
        }
    }

    /**
     * remove item task
     */
    fun removeItem(recyclerView: RecyclerView, position: Int){
        recyclerView.post{
            this.listTask?.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, this.listTask?.size!!)
        }
    }

    /**
     * update item task
     */
    fun updateItem(recyclerView: RecyclerView){
        recyclerView.post {
            notifyDataSetChanged()
        }
    }
}