package com.assignment.taskassignment.network.repository

import com.assignment.taskassignment.network.model.NewsListResponse
import com.assignment.taskassignment.network.repository.APIURL.Companion.NEWS_LIST
import retrofit2.Call
import retrofit2.http.GET



interface APIService {

    @GET(NEWS_LIST)
    fun makeRequest(): Call<NewsListResponse>
}