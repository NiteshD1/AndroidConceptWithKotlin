package com.android.concept.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.android.concept.HomeFragment
import com.android.concept.ProfileFragment
import com.android.concept.SettingFragment

class ViewpagerAdapter(
    var fragmentManager: FragmentManager,
    var tabCount : Int
) : FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> ProfileFragment()
            2 -> SettingFragment()
            else -> HomeFragment()
        }
    }
}