package com.example.administrator.paging.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.administrator.paging.network.Api
import java.util.concurrent.Executor

/**
 * 创建 DataSourceFactory，用于创建LiveData<PageList<T>>
 * @author  : Alex
 * @date    : 2018/08/21
 * @version : V 2.0.0
 */
class DataSourceFactroy( var api: Api,var retryExecutor: Executor) : DataSource.Factory<Int, StudentBean.StoriesBean>() {

    //创建观察的LivaData<DataSource> ,操作的改变都是修改sourceLivaData的值，触发系列操作
    val sourceLivaData = MutableLiveData<com.example.administrator.paging.paging.DataSource>()
    override fun create(): DataSource<Int, StudentBean.StoriesBean> {
        val dataSource = DataSource(api,retryExecutor)
        sourceLivaData.postValue(dataSource)
        return dataSource
    }
}