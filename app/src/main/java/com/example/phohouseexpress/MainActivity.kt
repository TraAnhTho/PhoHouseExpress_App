package com.example.phohouseexpress

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.phohouseexpress.R
import com.example.phohouseexpress.app_ui.home.MainHomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .commit()
        }
    }
}


