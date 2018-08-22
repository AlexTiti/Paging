package com.example.administrator.paging.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.ItemKeyedDataSource
import com.example.administrator.paging.network.Api
import com.example.library.helper.RxHelper
import java.util.concurrent.Executor


/**
 * @author  : Alex
 * @date    : 2018/08/21
 * @version : V 2.0.0
 */
class DataSource(var api: Api, var retryExecutor: Executor) : ItemKeyedDataSource<Int, StudentBean.StoriesBean>() {

    lateinit var date: String
    private var retry: (() -> Any)? = null

    val initialStatus by lazy {
        MutableLiveData<Resource<String>>()
    }

    val refreshStatus by lazy {
        MutableLiveData<Resource<String>>()
    }

    fun retryFailed() {
        val preRetry = retry
        retry = null
        preRetry.let {
            retryExecutor.execute {
                it?.invoke()
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<StudentBean.StoriesBean>) {
        refreshStatus.postValue(Resource.loading(null))
        initialStatus.postValue(Resource.loading(null))
        api.data.compose(RxHelper.rxSchedulerHelper())
                .subscribe({
                    refreshStatus.postValue(Resource.success())
                    initialStatus.postValue(Resource.success())
                    callback.onResult(it.stories!!)
                    date = it.date!!
                    retry = null
                }, {
                    refreshStatus.postValue(Resource.error(it.message))
                    initialStatus.postValue(Resource.error(it.message))
                    retry = {
                        loadInitial(params, callback)
                    }
                })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<StudentBean.StoriesBean>) {
        refreshStatus.postValue(Resource.loading(null))
        api.getDataByDate(date).compose(RxHelper.rxSchedulerHelper())
                .subscribe({
                    refreshStatus.postValue(Resource.success())
                    callback.onResult(it.stories!!)
                    date = it.date!!
                    retry = null
                }, {
                    retry = {
                        refreshStatus.postValue(Resource.error(it.message))
                        loadAfter(params, callback)
                    }
                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<StudentBean.StoriesBean>) {
    }

    override fun getKey(item: StudentBean.StoriesBean) = item.id
}


