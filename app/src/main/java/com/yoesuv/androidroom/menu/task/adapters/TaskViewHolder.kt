package com.yoesuv.androidroom.menu.task.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yoesuv.androidroom.databinding.ItemTaskBinding
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import com.yoesuv.androidroom.menu.task.viewmodels.ItemTaskViewModel

class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(myTask: MyTaskModel?, onItemClick:(myTask: MyTaskModel?) -> Unit){
        binding.itemTask = ItemTaskViewModel(myTask)
        binding.ivMenuMore.setOnClickListener {
            onItemClick(myTask)
        }
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): TaskViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemTaskBinding.inflate(inflater, parent, false)
            return TaskViewHolder(binding)
        }
    }

}