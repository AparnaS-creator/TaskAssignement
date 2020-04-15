package com.assignment.listapplication.network.di

import com.assignment.wiproassignment.AppModule
import com.assignment.wiproassignment.repository.RetrofitRepository
import com.assignment.wiproassignment.viewModel.RetroViewModelFactory

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,APIModule::class])
interface APIComponent {
    fun inject(retrofitRepository: RetrofitRepository)
    fun inject(retroViewModelFactory: RetroViewModelFactory)
}