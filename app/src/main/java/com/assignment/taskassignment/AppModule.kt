package com.assignment.taskassignment

import dagger.Module
import dagger.Provides

@Module
class AppModule constructor(myRetroApplication: MyApplication){

    var myApplication:MyApplication

    init {
        this.myApplication = myRetroApplication
    }

    @Provides
    fun provideMyApplication():MyApplication{
        return myApplication
    }
}