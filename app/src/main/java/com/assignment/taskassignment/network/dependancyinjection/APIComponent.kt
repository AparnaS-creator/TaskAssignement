package com.assignment.taskassignment.network.dependancyinjection

import com.assignment.taskassignment.AppModule
import com.assignment.taskassignment.network.repository.NewsRepository
import com.assignment.taskassignment.network.view.NewsFragment
import com.assignment.taskassignment.network.viewmodel.NewsViewModel
import com.assignment.taskassignment.network.viewmodel.NewsViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,APIModule::class])
interface APIComponent {
    fun inject(newsRepository: NewsRepository)
    fun inject(newsViewModel: NewsViewModel)
    fun inject(newsFragment: NewsFragment)
    fun inject(newsViewModelFactory:NewsViewModelFactory)
}