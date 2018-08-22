package com.example.administrator.paging

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.administrator.paging.paging.StudentBean

/**
 * @author  : Alex
 * @date    : 2018/08/21
 * @version : V 2.0.0
 */
class Adapter : PagedListAdapter<StudentBean.StoriesBean, Adapter.ViewHolder>(diffCallback) {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView = view.findViewById<TextView>(R.id.textView)
        val imageView = view.findViewById<ImageView>(R.id.imageView)

        fun bind(studentBean: StudentBean.StoriesBean) {
            textView.text = studentBean.title
            Glide.with(context).load(studentBean.images!![0]).into(imageView)
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<StudentBean.StoriesBean>() {

            override fun areItemsTheSame(oldItem: StudentBean.StoriesBean, newItem: StudentBean.StoriesBean): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: StudentBean.StoriesBean, newItem: StudentBean.StoriesBean): Boolean {
                return oldItem == newItem
            }
        }
    }
}