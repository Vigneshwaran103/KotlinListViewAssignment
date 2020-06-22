package com.metrolinx.presto.android.kotlinlistviewassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.accenture.codingassignment.viewModel.MainViewModel
import com.metrolinx.presto.android.kotlinlistviewassignment.Adapter.AdapterClass
import com.metrolinx.presto.android.kotlinlistviewassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: AdapterClass


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        initViewModel()
        setAdapter()
        callAPI()
        activityMainBinding.swipeRefresh.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            activityMainBinding.swipeRefresh.isRefreshing = true
            callAPI()
        })
    }

    fun initViewModel() {

        //initilizing the viewmodel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.setApplicationContext(this.baseContext)
    }

    private fun setAdapter() {
        //setting the adapter for the recyclerview
        adapter = AdapterClass()
        activityMainBinding.recyclerView.adapter = adapter
    }

    private fun callAPI() {
        if (AppConstant.isNetworkAvailable(this)) {
            //getting data from API
            viewModel.getDataFromAPI()
            setObserverForApiResponse()
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show()
            if (activityMainBinding.swipeRefresh.isRefreshing == true)
                activityMainBinding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setObserverForApiResponse() {
        viewModel.listData.observe(this, Observer {
            if (viewModel.isResponseFromAPI.value == true) {
                //setting the title and details on the view
                setTitle(it.title)
                adapter.updateList(it.rows)
                if (activityMainBinding.swipeRefresh.isRefreshing == true)
                    activityMainBinding.swipeRefresh.isRefreshing = false

                viewModel.updateValue()
            }
        })
    }


}
