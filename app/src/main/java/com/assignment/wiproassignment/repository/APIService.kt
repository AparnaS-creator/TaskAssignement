package com.assignment.wiproassignment.repository



import com.assignment.wiproassignment.model.newlist.NewsListResponse
import io.reactivex.Observable
import retrofit2.http.*
import retrofit2.http.GET




interface APIService {

    /**
     * Created by Aparna S.
     * @Base APIService interface :  This interface contain the all the methods
    of apis (Communicate to  servers with predefined parameters ).
     **/

    @GET(WebConstants.NEWS_LIST)
    fun getNewsList():  Observable<NewsListResponse>
}