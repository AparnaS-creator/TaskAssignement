package com.assignment.taskassignment

import android.app.Application
import android.content.Context
import com.assignment.taskassignment.network.dependancyinjection.APIComponent
import com.assignment.taskassignment.network.dependancyinjection.APIModule
import com.assignment.taskassignment.network.dependancyinjection.DaggerAPIComponent
import com.assignment.taskassignment.network.repository.APIURL




class MyApplication : Application() {


    companion object {
        var ctx: Context? = null
        lateinit var apiComponent:APIComponent
    }
    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        apiComponent = initDaggerComponent()

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