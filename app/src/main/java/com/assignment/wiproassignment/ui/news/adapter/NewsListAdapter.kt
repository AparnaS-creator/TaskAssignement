package com.assignment.wiproassignment.ui.news.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.assignment.wiproassignment.R
import com.assignment.wiproassignment.model.newlist.NewsListResponse
import com.assignment.wiproassignment.utill.showImage


/**
 * Created by Aparna S
 */
class NewsListAdapter(
    activity: Context?,
    private var allList: ArrayList<NewsListResponse.Row>?
) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    var context: Context? = activity
    var colorCode = ""
    var selectedPosition = -1

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val inflater = LayoutInflater.from(p0.context)
        return ViewHolder(
            inflater.inflate(
                R.layout.item_list,
                p0,
                false
            )
        )


    }

    override fun getItemCount(): Int {
        return allList!!.size
    }


    fun getCount(): Int {
        return allList!!.size
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val data = allList?.get(position)!!

            data.title?.let {
                holder.tvTitle.visibility=View.VISIBLE
                holder.tvTitle.text = data.title
            }
            data.description?.let {
                holder.tvDesc.visibility=View.VISIBLE
                holder.tvDesc.text = data.description
            }
            if (!data.imageHref.isNullOrEmpty()) {
                holder.ivThumbnail.visibility=View.VISIBLE
                showImage(holder.ivThumbnail, data.imageHref!!, R.drawable.border)
            } else {
                holder.ivThumbnail.setImageResource(R.drawable.border)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivThumbnail: ImageView = view.findViewById(R.id.ivThumbnail)
        val tvTitle: TextView = view.findViewById(R.id.tvHeading)
        val tvDesc: TextView = view.findViewById(R.id.tvDesc)

        var mIsInTheMiddle = false

        fun getIsInTheMiddle(): Boolean {
            return mIsInTheMiddle
        }
    }

    interface ItemClickListener {
        fun onItemClick(data: NewsListResponse.Row, position: Int)

    }


}

