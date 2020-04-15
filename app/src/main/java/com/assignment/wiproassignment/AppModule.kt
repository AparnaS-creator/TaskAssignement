package com.assignment.wiproassignment

import dagger.Module
import dagger.Provides

@Module
class AppModule constructor(myApplication: MyApplication){

    var myApplication:MyApplication

    init {
        this.myApplication = myApplication
    }

    @Provides
    fun provideMyApplication():MyApplication{
        return myApplication
    }
}