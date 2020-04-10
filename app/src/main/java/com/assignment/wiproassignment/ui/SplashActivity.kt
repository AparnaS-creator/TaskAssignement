package com.assignment.wiproassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.assignment.wiproassignment.ui.news.MainActivity
import com.assignment.wiproassignment.R
import com.assignment.wiproassignment.databinding.ActivitySplashBinding


/**
 * Created by Aparna S.
 */
class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        init()

    }

    /**
     * Init variables and set values
     */
    private fun init() {
        Handler().postDelayed({
            //getting instance
          goToDashboard()

        }, 2000)
    }

    override fun onBackPressed() {
        //nothing to do
    }

    /**
     *  navigation to Dashboard
     */
    private fun goToDashboard() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this@SplashActivity.finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)  // for open
    }


}

