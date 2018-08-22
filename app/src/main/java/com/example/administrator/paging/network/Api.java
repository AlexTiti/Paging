package com.example.administrator.paging.network;


import com.example.administrator.paging.paging.StudentBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/18
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public interface Api {

    public final String baseUrl = "http://news-at.zhihu.com";
    //http://news-at.zhihu.com/api/3/news/latest
    /**
     * 获取数据
     * @return Observable
     */
    @GET("/api/4/news/latest")
    Observable<StudentBean> getData();

    /**
     *
     * @param date
     * @return
     */
    @GET("/api/4/news/before/{date}")
    Observable<StudentBean> getDataByDate(@Path("date") String date);

    /**
     * 获取新闻详情
     * @param id
     * @return
     */
    @GET("/api/4/news/{id}")
    Observable<DetailBean> getDataDetail(@Path("id") String id);

}
