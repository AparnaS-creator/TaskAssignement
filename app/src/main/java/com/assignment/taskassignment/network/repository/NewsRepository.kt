package com.assignment.taskassignment.network.repository


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.taskassignment.MyApplication
import com.assignment.taskassignment.network.dependancyinjection.APIComponent
import com.assignment.taskassignment.network.dependancyinjection.DaggerAPIComponent
import com.assignment.taskassignment.network.model.NewsListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import android.widget.Toast



class NewsRepository {
    lateinit var apiComponent: APIComponent
    var newsMutableList: MutableLiveData<NewsListResponse> = MutableLiveData()
    @Inject
    lateinit var retrofit: Retrofit
    init {
       /* apiComponent =   DaggerAPIComponent
            .builder()
            .aPIModule(APIModule(APIURL.BASE_URL))
            .build()
        apiComponent.inject(this)*/

        var apiComponent :APIComponent =  MyApplication.apiComponent
        apiComponent.inject(this)
    }


    fun fetchNewsList(): LiveData<NewsListResponse> {

         var apiService:APIService = retrofit.create(APIService::class.java)
         var newsListInfo : Call<NewsListResponse> =  apiService.makeRequest()
        newsListInfo.enqueue(object :Callback<NewsListResponse>{
            override fun onFailure(call: Call<NewsListResponse>, t: Throwable) {
             Log.d("RetroRepository","Failed:::"+t.message)
            }

            override fun onResponse(call: Call<NewsListResponse>, response: Response<NewsListResponse>) {
                var newList = response.body()
                newsMutableList.value = newList

            }





        })

         return  newsMutableList

    }


}