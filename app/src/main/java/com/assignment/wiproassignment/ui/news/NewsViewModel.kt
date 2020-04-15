package com.assignment.wiproassignment.ui.news


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.wiproassignment.R
import com.assignment.wiproassignment.model.newlist.NewsListResponse
import com.assignment.wiproassignment.repository.ApiUtilities
import com.assignment.wiproassignment.repository.ErrorModel
import com.assignment.wiproassignment.repository.NetworkManager
import com.assignment.wiproassignment.repository.ServiceListener
import androidx.lifecycle.LiveData
import com.assignment.wiproassignment.model.newlist.NewsListResponse.Row







/**
 * Created by Aparna S.
 */
class NewsViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var apiError = MutableLiveData<String>()
    var apiResponse = MutableLiveData<Any>()
   var users: MutableLiveData<ArrayList<Row>>? = null

    init {
        isLoading.value = false
    }

    fun getUsers(): LiveData<ArrayList<Row>> {
        if (users == null) {
            users = MutableLiveData<ArrayList<Row>>()
            callNewsListApi()
        }
        return users as MutableLiveData<ArrayList<Row>>
    }


    /**
     * Get List Webservice Call
     */
    fun callNewsListApi(
    ) {
        try {
            if (isLoading.value == false) {
                isLoading.value = true
                val manager = NetworkManager()
                manager.createApiRequest(
                    ApiUtilities.getAPIService().getNewsList(),
                    object : ServiceListener<NewsListResponse> {
                        override fun getServerResponse(
                            response: NewsListResponse,
                            requestcode: Int
                        ) {
                            apiResponse.value = response
                            isLoading.value = false
                        }

                        override fun getError(error: ErrorModel, requestcode: Int) {
                            apiError.value = error.error_message
                            isLoading.value = false
                        }
                    })
            }
        } catch (e: Exception) {
            e.printStackTrace()
            apiError.value = ""
            isLoading.value = false
        }
    }






}