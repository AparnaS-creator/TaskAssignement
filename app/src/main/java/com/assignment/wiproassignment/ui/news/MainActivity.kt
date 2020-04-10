package com.assignment.wiproassignment.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.wiproassignment.R
import com.assignment.wiproassignment.databinding.ActivityMainBinding
import com.assignment.wiproassignment.model.newlist.NewsListResponse
import com.assignment.wiproassignment.ui.news.adapter.NewsListAdapter
import com.assignment.wiproassignment.utill.Utility.isNetworkAvailable
import com.assignment.wiproassignment.utill.showToast
import com.assignment.wiproassignment.widget.loader.UtilLoader

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsListAdapter
    private lateinit var mUtilLoader: UtilLoader

    private var previousSelected = -1
    private var onClick = false
    private var onFavClick = false
    private var currentPosition = 0
    var swipeCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = NewsViewModel()
        binding.viewModel = viewModel
        init()
        attachObserver()
        callAPI()


        binding.swipeRefreshLayout.setOnRefreshListener {

            swipeCount += 1;
            if (swipeCount > 0) {
                callAPI()
                //blogs.add(Blog("Blog Title $swipeCount", "Description : Blog description goes here"))
                Toast.makeText(this, "Swipe called", Toast.LENGTH_SHORT).show()
            }
            adapter.notifyDataSetChanged()

            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    /**
     * Init variables and set values
     */
    private fun init() {
        mUtilLoader = UtilLoader(this)
        val supportList = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNews.layoutManager = supportList
    }

 
    /**
     * call apis
     */
    private fun callAPI() {
        if (isNetworkAvailable(this@MainActivity)) {
            viewModel.callNewsListApi(
                this@MainActivity
            )
        } else {
            noNewsFound()
            showToast(this@MainActivity, getString(R.string.no_internet),Toast.LENGTH_SHORT)

        }
    }

    /**
     * when no doctors available
     */
    private fun noNewsFound() {
        binding.tvRecordFound.visibility = View.VISIBLE
        binding.rvNews.visibility = View.GONE
    }

    /**
     * attach observer for api calls
     */
    private fun attachObserver() {
        viewModel.isLoading.observe(this, androidx.lifecycle.Observer<Boolean> {
            it?.let {
                showLoadingDialog(it)
            }
        })
        viewModel.apiError.observe(this, androidx.lifecycle.Observer<String> {
            it?.let {
                //nothing to do
              showToast(this@MainActivity, getString(R.string.no_record),Toast.LENGTH_SHORT)
            }
        })
        viewModel.apiResponse.observe(this, androidx.lifecycle.Observer<Any> {
            it?.let {
                if (it is NewsListResponse) {
                    try {
                        
                           
                                when {
                                    it.rows == null -> noNewsFound()
                                    it.rows!!.size > 0 -> {
                                        binding.tvRecordFound.visibility = View.GONE
                                        binding.rvNews.visibility = View.VISIBLE
                                        adapter =
                                            NewsListAdapter(
                                                this@MainActivity,
                                                it.rows!!
                                            )
                                        binding.rvNews.adapter = adapter
                                        adapter.notifyDataSetChanged()
                                    }
                                    else -> noNewsFound()
                                }
                           
                        
                        
                    } catch (e: Exception) {
                        e.printStackTrace()
                        noNewsFound()
                    }
                
                }
            }
        })
    }

    /**
     * show / hide loader
     */
    private fun showLoadingDialog(show: Boolean) {
        if (show) mUtilLoader.startLoader(this) else mUtilLoader.stopLoader()
    }

    /**
     * update doc ui
     */
    private fun updateUI() {
        Handler().postDelayed({
            if (adapter.getCount() == 0) {
                noNewsFound()
            }
        }, 300)
    }

    override fun onResume() {
        super.onResume()
        try {
           /* AppInstance.newsDataObj = getNe(this)
            clearDocData()*/
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    
}
