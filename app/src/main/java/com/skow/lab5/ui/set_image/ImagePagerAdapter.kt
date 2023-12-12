package com.skow.lab5.ui.set_image

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.skow.lab5.R
import java.lang.IllegalArgumentException

class ImagePagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {

        return when (position){
            0 -> FragmentImage(R.drawable.kai)
            1 -> FragmentImage(R.drawable.zane)
            2 -> FragmentImage(R.drawable.jay)
            3 -> FragmentImage(R.drawable.cole)
            else -> throw IllegalArgumentException("Illegal position")
        }
    }

    override fun getItemCount(): Int {
        return 4
    }
}