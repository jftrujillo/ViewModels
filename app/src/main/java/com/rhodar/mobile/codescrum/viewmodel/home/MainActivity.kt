package com.rhodar.mobile.codescrum.viewmodel.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rhodar.mobile.codescrum.viewmodel.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                    .add(R.id.screen_container,ListFragment())
                    .commit()
        }
    }
}
