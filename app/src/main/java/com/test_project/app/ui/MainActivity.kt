package com.test_project.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test_project.app.R


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: mainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        getData()
    }

    private fun getData() {
        viewModel.saveObject()
        // using Rx
        viewModel.RetrieveData()
        // using LiveData
        subcribeObservers()
    }

    private fun subcribeObservers() {
        viewModel.getEstimateData()?.observe(this, Observer {
            if (it != null)
                viewModel.getPersonData(it.created_by!!)?.observe(this, Observer {
                    if (it != null) {
                        // user data
                    }
                })
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(mainViewModel::class.java)
    }

}