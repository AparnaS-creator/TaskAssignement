package com.assignment.taskassignment.network.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.taskassignment.network.dependancyinjection.DaggerAPIComponent
import com.assignment.taskassignment.network.model.NewsListResponse
import com.assignment.taskassignment.network.repository.NewsRepository

class NewsViewModel(newsRepository: NewsRepository): ViewModel() {

    lateinit var newsRepository:NewsRepository
    var newsLiveData: LiveData<NewsListResponse> = MutableLiveData()

    init {
        this.newsRepository  = newsRepository
        fetchNewsFromRepository()
        }

    fun fetchNewsFromRepository(){
        newsLiveData =  newsRepository.fetchNewsList()
    }


}