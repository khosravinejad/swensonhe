package com.morteza.swensonhe.presentation.forecast

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ForecastPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val fragments = mutableListOf<ForecastFragment>()

    fun addFragment(fragment: ForecastFragment) {
        fragments.add(fragment)
        notifyItemInserted(itemCount - 1)
    }

    fun addFragments(list: List<ForecastFragment>) {
        fragments.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}