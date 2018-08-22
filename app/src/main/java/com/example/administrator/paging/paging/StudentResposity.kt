package com.example.administrator.paging.paging

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.example.administrator.paging.network.Api
import java.util.concurrent.Executor

/**
 * @author  : Alex
 * @date    : 2018/08/21
 * @version : V 2.0.0
 */
class StudentResposity(var api: Api,var retryExecutor: Executor) : Resposity<StudentBean.StoriesBean> {
    /**
     *
     */
    override fun getDataList(pageSize: Int): Listing<StudentBean.StoriesBean> {
        val pageConfig = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setPrefetchDistance(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build()

        val stuDataSourceFactroy = DataSourceFactroy(api,retryExecutor)
        val pagedList = LivePagedListBuilder(stuDataSourceFactroy, pageConfig)
        val refreshState = Transformations.switchMap(stuDataSourceFactroy.sourceLivaData) { it.refreshStatus }
        val initialStatus = Transformations.switchMap(stuDataSourceFactroy.sourceLivaData) { it.initialStatus }

        return Listing<StudentBean.StoriesBean>(
                pagedList.build(),
                initialStatus,
                refreshState,
                refresh = {
                    stuDataSourceFactroy.sourceLivaData.value?.invalidate()
                },
                retry = {
                    stuDataSourceFactroy.sourceLivaData.value?.retryFailed()
                }
        )
    }
}