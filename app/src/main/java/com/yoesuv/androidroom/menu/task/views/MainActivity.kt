package com.yoesuv.androidroom.menu.task.views

import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.databinding.ActivityMainBinding
import com.yoesuv.androidroom.menu.task.adapters.ListTaskAdapter
import com.yoesuv.androidroom.menu.task.models.MyTaskModel
import com.yoesuv.androidroom.menu.task.viewmodels.MainViewModel
import com.yoesuv.androidroom.utils.Utility
import com.yoesuv.androidroom.utils.dialogDeleteAll
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

        if (Utility.isVanillaIceCreamAndUp()) {
            enableEdgeToEdge()
            Utility.insetsPadding(binding.root, left = true, right = true)
            Utility.insetsPadding(
                binding.appBarLayoutMain,
                top = true,
                color = getColor(R.color.pink_500)
            )
            Utility.insetsPadding(binding.recyclerViewMain, bottom = true)
        }
        adjustFab()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionDeleteAll) {
            showDialogDeleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.main = viewModel

        viewModel.listTask.observe(this) {
            onListDataChange(it)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.title = getString(R.string.my_task)
    }

    private fun setupRecycler() {
        binding.recyclerViewMain.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        adapter = ListTaskAdapter(listTask) { position, task ->
            dialogMenu(this, {
                showDialogEdit(task, position)
            }, {
                removeTask(task, position)
            })
        }
        binding.recyclerViewMain.adapter = adapter
    }

    private fun onListDataChange(listTask: List<MyTaskModel>) {
        if (listTask.isNotEmpty()) {
            this.listTask.clear()
            for (i: Int in 0 until (listTask.size)) {
                this.listTask.add(listTask[i])
            }
            adapter.notifyItemRangeChanged(0, listTask.size)
        }
    }

    private fun showDialogEdit(data: MyTaskModel?, position: Int) {
        dialogInsertUpdateTask(this, data) { title, content ->
            data?.titleTask = title
            data?.contentTask = content
            viewModel.updateTask(data)
            adapter.notifyItemChanged(position)
        }
    }

    private fun showDialogDeleteAll() {
        dialogDeleteAll(this) {
            viewModel.deleteAll()
            adapter.notifyItemRangeRemoved(0, listTask.size)
            listTask.clear()
        }
    }

    private fun removeTask(task: MyTaskModel?, position: Int) {
        viewModel.deleteTask(task)
        listTask.removeAt(position)
        adapter.notifyItemRemoved(position)
        adapter.notifyItemRangeChanged(position, listTask.size)
    }

    private fun adjustFab() {
        if (Utility.isVanillaIceCreamAndUp()) {
            ViewCompat.setOnApplyWindowInsetsListener(binding.fabMain) { view, windowInsets ->
                val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

                val extra = resources.getDimensionPixelSize(R.dimen.fab_margin)
                // Apply margin to FAB to avoid overlapping with navigation bar
                view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    bottomMargin = insets.bottom + extra
                    rightMargin = insets.right + extra
                }

                WindowInsetsCompat.CONSUMED
            }
        }
    }

}
