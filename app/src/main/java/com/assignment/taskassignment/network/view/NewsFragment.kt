package com.assignment.taskassignment.network.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.taskassignment.R
import com.assignment.taskassignment.databinding.FragmentListBinding
import com.assignment.taskassignment.loader.UtilLoader
import com.assignment.taskassignment.network.model.NewsListResponse
import com.assignment.taskassignment.network.util.Utility.isNetworkAvailable
import com.assignment.taskassignment.network.util.showToast
import com.assignment.taskassignment.network.viewmodel.NewsViewModel
import com.assignment.taskassignment.network.viewmodel.NewsViewModelFactory
import kotlinx.android.synthetic.main.toolbar.view.*

class NewsFragment: Fragment(),NewsListAdapter.ItemClickListener {

    private var onClick = false
    lateinit var newsViewModel: NewsViewModel
    var fragmentView:View?=null
    private var  newsListLayoutBinding:FragmentListBinding?=null
    private var listAdapter: NewsListAdapter?=null
    private var previousSelected = -1
    private lateinit var mUtilLoader: UtilLoader
    private var contextData: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        newsListLayoutBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_list,container,false)
        fragmentView = newsListLayoutBinding?.root
        contextData= container!!.context
        initAdapter()
        setAdapter()
        fetchNewsInfo()

        return  fragmentView
    }

    fun  initViewModel(){
        var retroViewModelFactory = NewsViewModelFactory()
        newsViewModel = ViewModelProviders.of(this,retroViewModelFactory).get(NewsViewModel::class.java)

        newsListLayoutBinding?.swipeRefreshLayout?.setOnRefreshListener {

            fetchNewsInfo()
            listAdapter?.notifyDataSetChanged()
            newsListLayoutBinding?.swipeRefreshLayout?.isRefreshing = false

        }
    }

    fun fetchNewsInfo(){
        if(contextData!=null){
            if (isNetworkAvailable(this.context!!)) {
            newsViewModel.newsLiveData?.observe(this, object : Observer<NewsListResponse> {
                override fun onChanged(t: NewsListResponse?) {
                    t?.apply {
                        listAdapter?.setAdapterList(t.rows!!)
                    }
                }

            })
        }
        else{
                noNewsFound()
                showToast(this.context!!, getString(R.string.internet_issue), Toast.LENGTH_SHORT)

            }
        }

    }

    private fun setAdapter(){

        newsListLayoutBinding?.rvNews?.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            adapter = listAdapter
        }

    }


    /**
     * when no News available
     */
    private fun noNewsFound() {
        newsListLayoutBinding?.tvRecordFound?.visibility = View.VISIBLE
        newsListLayoutBinding?.rvNews?.visibility = View.GONE
    }

   private fun initAdapter(){
       listAdapter = NewsListAdapter(this@NewsFragment.requireActivity())
   }

    override fun onItemClick(data: NewsListResponse.Row, position: Int) {
        if (!onClick) {
            onClick = true
            if (position != previousSelected) {
                previousSelected = position
            }

            newsListLayoutBinding?.idToolbar?.tvTitle?.text=data.title

        }
    }

}
