package com.assignment.wiproassignment

import android.app.Application
import android.content.Context
import com.assignment.listapplication.network.di.APIComponent
import com.assignment.listapplication.network.di.APIModule
import com.assignment.listapplication.network.di.DaggerAPIComponent
import com.assignment.wiproassignment.repository.APIURL


/**
 * Created by Aparna S
 * The class will start once the application will start and will set the Splunk Key for handling
 *
 */

class MyApplication : Application() {


    companion object {
        var ctx: Context? = null
        lateinit var apiComponent: APIComponent
    }
    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        //apiComponent = initDaggerComponent()

    }

    fun getMyComponent(): APIComponent {
        return apiComponent
    }

    fun initDaggerComponent(): APIComponent {
        apiComponent =   DaggerAPIComponent
            .builder()
            .aPIModule(APIModule(APIURL.BASE_URL))
            .build()
        return  apiComponent

    }

}
