package com.example.administrator.paging.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel


/**
 * @author  : Alex
 * @date    : 2018/08/21
 * @version : V 2.0.0
 */
class ViewModelSrudent(studentResposity: StudentResposity) : ViewModel() {
    //开始时建立DataSource和LiveData<Ling<StudentBean>>的连接
    val data = MutableLiveData<String>()
    // map的数据修改时，会执行studentResposity 重新创建 LiveData<Ling<StudentBean>>
    private val repoResult = Transformations.map(data) {
        studentResposity.getDataList(10)
    }

    // c从Ling对象中获取要观察的数据，调用switchMap当repoResult 修改时会自动更新 生成的LiveData
    val pagedList = Transformations.switchMap(repoResult) { it.pagedList }
    val initialStatus = Transformations.switchMap(repoResult) { it.initialStatus }
    val refreshState = Transformations.switchMap(repoResult) { it.refreshState }

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }

    fun showData(string: String): Boolean {
        if (data.value == string)
            return false
        data.value = string
        return true
    }

    fun retry() {
        repoResult.value?.retry?.invoke()
    }

}