package com.assignment.wiproassignment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.wiproassignment.model.newlist.NewsListResponse
import com.assignment.wiproassignment.repository.RetrofitRepository


import javax.inject.Inject

class RetroViewModel(retrofitRepository: RetrofitRepository): ViewModel() {

    lateinit var retrofitRepository:RetrofitRepository
    var postInfoLiveData: LiveData<List<NewsListResponse.Row>> = MutableLiveData()

    init {
        this.retrofitRepository  = retrofitRepository
        fetchPostInfoFromRepository()
        }

    fun fetchPostInfoFromRepository(){
        postInfoLiveData =  retrofitRepository.fetchPostInfoList()
    }


}