package com.assignment.wiproassignment.repository


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.assignment.listapplication.network.di.APIComponent
import com.assignment.wiproassignment.MyApplication
import com.assignment.wiproassignment.model.newlist.NewsListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitRepository {
    lateinit var apiComponent: APIComponent
    var postInfoMutableList: MutableLiveData<List<NewsListResponse.Row>> = MutableLiveData()
    @Inject
    lateinit var retrofit: Retrofit
    init {
       /* apiComponent =   DaggerAPIComponent
            .builder()
            .aPIModule(APIModule(APIURL.BASE_URL))
            .build()
        apiComponent.inject(this)*/

        var apiComponent : APIComponent =  MyApplication.apiComponent
        apiComponent.inject(this)
    }


    fun fetchPostInfoList(): LiveData<List<NewsListResponse.Row>> {

         /*var apiService:APIService = retrofit.create(APIService::class.java)
         var postListInfo : Call<List<NewsListResponse.Row>> =  apiService.makeRequest()
        postListInfo.enqueue(object :Callback<List<NewsListResponse.Row>>{
            override fun onFailure(call: Call<List<NewsListResponse.Row>>, t: Throwable) {
             Log.d("RetroRepository","Failed:::"+t.message)
            }

            override fun onResponse(call: Call<List<NewsListResponse.Row>>, response: Response<List<NewsListResponse.Row>>) {
                var postInfoList = response.body()
                postInfoMutableList.value = postInfoList

            }
        })

         return  postInfoMutableList*/
        return  postInfoMutableList

    }


}