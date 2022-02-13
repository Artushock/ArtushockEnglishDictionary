package com.artushock.artushockenglishdictionary.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.artushock.artushockenglishdictionary.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.main_container, SearchFragment())
                .commit()
        }
    }
}