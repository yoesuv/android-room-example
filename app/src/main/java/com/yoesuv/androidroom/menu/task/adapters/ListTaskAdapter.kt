package com.yoesuv.androidroom.menu.task.adapters

import android.content.Context
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.databinding.ItemTaskBinding
import com.yoesuv.androidroom.menu.task.AdapterOnClickListener
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import com.yoesuv.androidroom.menu.task.viewmodels.ItemTaskViewModel

class ListTaskAdapter(context: Context, private var listTask: MutableList<MyTaskModel>?, private val adapterOnClickListener: AdapterOnClickListener):
        RecyclerSwipeAdapter<ListTaskAdapter.TaskViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding: ItemTaskBinding = DataBindingUtil.inflate(inflater, R.layout.item_task, parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listTask?.size!!
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.setupItem(listTask!![holder.adapterPosition], adapterOnClickListener, holder.adapterPosition)
        mItemManger.bindView(holder.itemView, holder.adapterPosition)
    }

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipeLayoutItemTask
    }

    /**
     * remove item task
     */
    fun removeItem(recyclerView: RecyclerView, position: Int){
        recyclerView.post{
            this.listTask?.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, this.listTask?.size!!)
            mItemManger.closeAllItems()
        }
    }

    /**
     * update item task
     */
    fun updateItem(recyclerView: RecyclerView){
        recyclerView.post {
            notifyDataSetChanged()
            mItemManger.closeAllItems()
        }
    }

    class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setupItem(myTask: MyTaskModel, adapterOnClickListener: AdapterOnClickListener, position: Int){
            binding.itemTask = ItemTaskViewModel(myTask, adapterOnClickListener, position)
            binding.swipeLayoutItemTask.showMode = SwipeLayout.ShowMode.LayDown
            binding.swipeLayoutItemTask.addDrag(SwipeLayout.DragEdge.Left, binding.bottomWrapper)
        }
    }
}