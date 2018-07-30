package com.yoesuv.androidroom.menu.task.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.daimajia.swipe.SimpleSwipeListener
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import com.daimajia.swipe.implments.SwipeItemAdapterMangerImpl
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.data.AppConstant
import com.yoesuv.androidroom.databinding.ItemTaskBinding
import com.yoesuv.androidroom.menu.task.models.MyTask
import com.yoesuv.androidroom.menu.task.viewmodels.ItemTaskViewModel

class ListTaskAdapter(context: Context, private var listTask: MutableList<MyTask>?): RecyclerSwipeAdapter<ListTaskAdapter.TaskViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    //val mItemManager: SwipeItemAdapterMangerImpl = SwipeItemAdapterMangerImpl(this)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding: ItemTaskBinding = DataBindingUtil.inflate(inflater, R.layout.item_task, parent, false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listTask?.size!!
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.setupItem(listTask!![holder.adapterPosition])
        mItemManger.bindView(holder.itemView, holder.adapterPosition)
    }

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipeLayoutItemTask
    }

    class TaskViewHolder(private val binding: ItemTaskBinding?) : RecyclerView.ViewHolder(binding?.root) {
        fun setupItem(myTask: MyTask){
            binding?.itemTask = ItemTaskViewModel(myTask)

            /*binding?.itemContent?.addOnLayoutChangeListener { p0, p1, top, p3, bottom, p5, p6, p7, p8 ->
                Log.d(AppConstant.TAG_DEBUG,"${myTask.titleTask} height = ${bottom-top}")
                val height = bottom-top
                val params = binding.bottomWrapper.layoutParams
                params.width = LinearLayout.LayoutParams.MATCH_PARENT
                params.height = height
                binding.bottomWrapper.layoutParams = params
                binding.bottomWrapper.requestLayout()
            }*/

            binding?.swipeLayoutItemTask?.showMode = SwipeLayout.ShowMode.LayDown
            binding?.swipeLayoutItemTask?.addDrag(SwipeLayout.DragEdge.Left, binding.bottomWrapper)
            binding?.swipeLayoutItemTask?.addSwipeListener(object : SimpleSwipeListener(){
                override fun onOpen(layout: SwipeLayout?) {
                    super.onOpen(layout)
                    Log.d(AppConstant.TAG_DEBUG,"Swipe Layout Open")
                }
            })
        }
    }
}