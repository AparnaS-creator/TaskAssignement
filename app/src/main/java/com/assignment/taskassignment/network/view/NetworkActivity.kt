package com.assignment.taskassignment.network.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.assignment.taskassignment.R


class NetworkActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.network_layout)
        replaceFragment()


    }



    fun replaceFragment(){
       supportFragmentManager
           .beginTransaction()
           .replace(R.id.container_retro_room,NewsFragment())
           .commit()
    }
}
