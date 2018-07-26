package com.yoesuv.androidroom.menu.task.views

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yoesuv.androidroom.R
import com.yoesuv.androidroom.databinding.ActivityMainBinding
import com.yoesuv.androidroom.menu.task.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = MainViewModel()

        setupBinding()
        setupToolbar()
    }

    private fun setupToolbar(){
        setSupportActionBar(toolbarMain)
        supportActionBar?.title = "My Task"
    }

    private fun setupBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.main = viewModel
    }
}
