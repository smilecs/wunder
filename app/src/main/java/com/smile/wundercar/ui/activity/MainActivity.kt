package com.smile.wundercar.ui.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import com.smile.wundercar.R
import com.smile.wundercar.ui.fragment.MapvVewFragment
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
        with(container) {
            adapter = SectionsAdapter(supportFragmentManager)
            currentItem = 0
            offscreenPageLimit = LIMIT
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    companion object {
        const val LIMIT = 2

        class SectionsAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> VehicleFragment()
                    else -> MapvVewFragment()
                }
            }

            override fun getCount() = LIMIT
        }
    }
}
