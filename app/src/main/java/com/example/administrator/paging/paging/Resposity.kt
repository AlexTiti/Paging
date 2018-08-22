package com.example.administrator.paging.paging

import com.example.administrator.paging.network.Api

/**
 * @author  : Alex
 * @date    : 2018/08/21
 * @version : V 2.0.0
 */
interface Resposity<T> {
    fun getDataList(pageSize: Int): Listing<T>
}