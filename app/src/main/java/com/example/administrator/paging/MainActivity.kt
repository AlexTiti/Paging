package com.example.administrator.paging

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.administrator.paging.network.Api
import com.example.administrator.paging.paging.Status
import com.example.administrator.paging.paging.StudentResposity
import com.example.administrator.paging.paging.ViewModelSrudent
import com.example.library.helper.RetrofitCreateHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    val model by lazy {
        initViewModel()
    }
    val adapter by lazy {
        Adapter()
    }

    private val NETWORK_IO = Executors.newFixedThreadPool(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSwipe()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter
        model.pagedList.observe(this, Observer {
            adapter.submitList(it)
        })
        model.showData("A")
        retry.setOnClickListener {
            model.retry()
        }
    }


    private fun initSwipe() {
        swipeRefresh.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW)
        swipeRefresh.setOnRefreshListener { model.refresh() }
//        model.refreshState.observe(this, Observer {
//            swipeRefresh.isRefreshing = it?.status == Status.SUCCESS
//        })

        model.refreshState.observe(this, Observer {
            when (it?.status) {
                Status.LOADING -> {
                    swipeRefresh.isRefreshing = true
                }
                Status.ERROR -> {
                    retry.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    swipeRefresh.isRefreshing = false
                }
                Status.SUCCESS -> {
                    retry.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    swipeRefresh.isRefreshing = false
                }
            }
        })
    }

    private fun initViewModel(): ViewModelSrudent {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val api = RetrofitCreateHelper.createApi(Api::class.java, Api.baseUrl)
                val studentResposity = StudentResposity(api, NETWORK_IO)

                return ViewModelSrudent(studentResposity) as T
            }
        })[ViewModelSrudent::class.java]
    }
}
