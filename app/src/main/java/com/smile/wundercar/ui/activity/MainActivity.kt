package com.smile.wundercar.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.smile.wundercar.R
import com.smile.wundercar.ui.fragment.MapVewFragment
import com.smile.wundercar.ui.fragment.VehicleFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                navItemSelector(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_map -> {
                navItemSelector(1)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun navItemSelector(pos: Int): Boolean {
        container.currentItem = pos
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQ_CODE)
        } else {
            initView()
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun initView() {
        with(container) {
            adapter = SectionsAdapter(supportFragmentManager)
            currentItem = 0
            offscreenPageLimit = LIMIT
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQ_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initView()
            }
        }
    }

    companion object {
        const val LIMIT = 2
        const val REQ_CODE = 1001

        class SectionsAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> VehicleFragment()
                    else -> MapVewFragment()
                }
            }

            override fun getCount() = LIMIT
        }
    }
}
