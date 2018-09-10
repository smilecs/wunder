package com.smile.wundercar.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.smile.wundercar.R
import com.smile.wundercar.ui.fragment.VehicleFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, VehicleFragment())
                .commit()
    }
}
