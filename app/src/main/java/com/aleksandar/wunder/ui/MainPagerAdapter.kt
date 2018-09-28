package com.aleksandar.wunder.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MainPagerAdapter(private val fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val tabNames = arrayOf("Info", "Map")

    override fun getItem(position: Int): Fragment {
        if (position == 0)return InfoFragment()
        else return MapFragment()
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabNames[position]
    }
}