package com.skow.lab5.ui.set_image

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.skow.lab5.R
import com.skow.lab5.databinding.FragmentSetImageBinding
import com.skow.lab5.ui.home.HomeViewModel

class FragmentSetImage : Fragment(){
    private var _binding: FragmentSetImageBinding? = null
    private lateinit var viewPager: ViewPager2

    private val sharedViewModel: HomeViewModel by activityViewModels()

    private val  binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSetImageBinding.inflate(inflater, container, false)

        viewPager = binding.viewPager
        val adapter = ImagePagerAdapter(requireActivity())
        viewPager.adapter = adapter

//        when (sharedViewModel.imgId.value){
//            R.drawable.kai -> viewPager.currentItem = 0
//            R.drawable.zane -> viewPager.currentItem = 1
//            R.drawable.jay -> viewPager.currentItem = 2
//            R.drawable.cole -> viewPager.currentItem = 3
//        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        if (::viewPager.isInitialized)
            when (sharedViewModel.imgId.value){
                R.drawable.kai -> viewPager.setCurrentItem(0, false)
                R.drawable.zane -> viewPager.setCurrentItem(1, false)
                R.drawable.jay -> viewPager.setCurrentItem(2, false)
                R.drawable.cole -> viewPager.setCurrentItem(3, false)
            }
//            Log.i("adapter", viewPager.currentItem.toString())
    }
}