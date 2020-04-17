package com.assignment.taskassignment.network.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.taskassignment.MyApplication
import com.assignment.taskassignment.network.dependancyinjection.APIComponent
import com.assignment.taskassignment.network.dependancyinjection.APIModule
import com.assignment.taskassignment.network.dependancyinjection.DaggerAPIComponent
import com.assignment.taskassignment.network.repository.APIURL
import com.assignment.taskassignment.network.repository.NewsRepository
import javax.inject.Inject


class NewsViewModelFactory : ViewModelProvider.Factory {
    lateinit var apiComponent: APIComponent
    @Inject
    lateinit var newsRepository: NewsRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
     //   initDaggerComponent()
       var apiComponent :APIComponent =  MyApplication.apiComponent
        apiComponent.inject(this)
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    fun initDaggerComponent(){
        apiComponent =   DaggerAPIComponent
            .builder()
            .aPIModule(APIModule(APIURL.BASE_URL))
            .build()
        apiComponent.inject(this)
    }
}