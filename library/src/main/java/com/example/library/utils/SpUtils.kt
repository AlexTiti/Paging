package com.example.library.utils


import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonParser

import java.util.ArrayList

/**
 * SharedPreferences工具类封装
 */
class SpUtils {

    /**
     * 设置preferencesName
     *
     * @param preferencesName preferencesName
     */
    private fun setPreferencesName(preferencesName: String) {
        mPreferencesName = preferencesName
    }

    companion object {
        private var sp: SharedPreferences? = null
        private var mPreferencesName = "share_preference_default"

        /**
         * 写入boolean变量至sp中
         *
         * @param ctx   上下文环境
         * @param key   存储节点名称
         * @param value 存储节点的值
         */
        fun putBoolean(ctx: Context, key: String, value: Boolean) {
            //(存储节点文件名称,读写方式)
            if (sp == null) {
                sp = ctx.getSharedPreferences(mPreferencesName, Context
                        .MODE_PRIVATE)
            }
            sp!!.edit().putBoolean(key, value).apply()
        }

        /**
         * 读取boolean标示从sp中
         *
         * @param ctx      上下文环境
         * @param key      存储节点名称
         * @param defValue 没有此节点默认值
         * @return 默认值或者此节点读取到的结果
         */
        fun getBoolean(ctx: Context, key: String, defValue: Boolean): Boolean {
            //(存储节点文件名称,读写方式)
            if (sp == null) {
                sp = ctx.getSharedPreferences(mPreferencesName, Context
                        .MODE_PRIVATE)
            }
            return sp!!.getBoolean(key, defValue)
        }

        /**
         * 写入String变量至sp中
         *
         * @param ctx   上下文环境
         * @param key   存储节点名称
         * @param value 存储节点的值
         */
        fun putString(ctx: Context, key: String, value: String) {
            //(存储节点文件名称,读写方式)
            if (sp == null) {
                sp = ctx.getSharedPreferences(mPreferencesName, Context
                        .MODE_PRIVATE)
            }
            sp!!.edit().putString(key, value).apply()
        }

        /**
         * 读取String标示从sp中
         *
         * @param ctx      上下文环境
         * @param key      存储节点名称
         * @param defValue 没有此节点默认值
         * @return 默认值或者此节点读取到的结果
         */
        fun getString(ctx: Context, key: String, defValue: String?): String? {
            //(存储节点文件名称,读写方式)
            if (sp == null) {
                sp = ctx.getSharedPreferences(mPreferencesName, Context
                        .MODE_PRIVATE)
            }
            return sp!!.getString(key, defValue)
        }


        /**
         * 写入int变量至sp中
         *
         * @param ctx   上下文环境
         * @param key   存储节点名称
         * @param value 存储节点的值
         */
        fun putInt(ctx: Context, key: String, value: Int) {
            //(存储节点文件名称,读写方式)
            if (sp == null) {
                sp = ctx.getSharedPreferences(mPreferencesName, Context
                        .MODE_PRIVATE)
            }
            sp!!.edit().putInt(key, value).apply()
        }

        /**
         * 读取int标示从sp中
         *
         * @param ctx      上下文环境
         * @param key      存储节点名称
         * @param defValue 没有此节点默认值
         * @return 默认值或者此节点读取到的结果
         */
        fun getInt(ctx: Context, key: String, defValue: Int): Int {
            //(存储节点文件名称,读写方式)
            if (sp == null) {
                sp = ctx.getSharedPreferences(mPreferencesName, Context
                        .MODE_PRIVATE)
            }
            return sp!!.getInt(key, defValue)
        }


        /**
         * 从sp中移除指定节点
         *
         * @param ctx 上下文环境
         * @param key 需要移除节点的名称
         */
        fun remove(ctx: Context, key: String) {
            if (sp == null) {
                sp = ctx.getSharedPreferences(mPreferencesName, Context
                        .MODE_PRIVATE)
            }
            sp!!.edit().remove(key).apply()
        }

        /**
         * 保存List
         *
         * @param key      sp key值
         * @param datalist list
         * @param <T>      item 类型
        </T> */
        fun <T> setDataList(key: String, datalist: List<T>?) {
            if (null == datalist || datalist.size <= 0) {
                return
            }

            val gson = Gson()
            //转换成json数据，再保存
            val strJson = gson.toJson(datalist)
            SpUtils.putString(AppUtils.context, key, strJson)
        }

        /**
         * 获取List
         *
         * @param key sp key值
         * @param <T> item 类型
         * @return list
        </T> */
        fun <T> getDataList(key: String, cls: Class<T>): List<T> {
            val dataList = ArrayList<T>()
            val strJson = SpUtils.getString(AppUtils.context, key, null) ?: return dataList

            try {
                val gson = Gson()
                val array = JsonParser().parse(strJson).asJsonArray
                for (elem in array) {
                    dataList.add(gson.fromJson(elem, cls))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return dataList
        }

        fun getThemeIndex(context: Context): Int {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return prefs.getInt("ThemeIndex", 5)
        }

        fun setThemeIndex(context: Context, index: Int) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            prefs.edit().putInt("ThemeIndex", index).apply()
        }

        fun getNightModel(context: Context): Boolean {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return prefs.getBoolean("pNightMode", false)
        }

        fun setNightModel(context: Context, nightModel: Boolean) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            prefs.edit().putBoolean("pNightMode", nightModel).apply()
        }
    }
}
