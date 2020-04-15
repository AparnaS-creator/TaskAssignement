package com.assignment.wiproassignment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.listapplication.network.di.APIComponent
import com.assignment.listapplication.network.di.APIModule
import com.assignment.wiproassignment.MyApplication
import com.assignment.wiproassignment.repository.APIURL
import com.assignment.wiproassignment.repository.RetrofitRepository


import javax.inject.Inject


class RetroViewModelFactory : ViewModelProvider.Factory {
    lateinit var apiComponent: APIComponent
    @Inject
    lateinit var retrofitRepository: RetrofitRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
     //   initDaggerComponent()
       var apiComponent :APIComponent =  MyApplication.apiComponent
        apiComponent.inject(this)
        if (modelClass.isAssignableFrom(RetroViewModel::class.java)) {
            return RetroViewModel(retrofitRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

 /*   fun initDaggerComponent(){
        apiComponent =   DaggerAPIComponent
            .builder()
            .aPIModule(APIModule(APIURL.BASE_URL))
            .build()
        apiComponent.inject(this)
    }*/
}