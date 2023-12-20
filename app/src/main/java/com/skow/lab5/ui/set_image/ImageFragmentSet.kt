package com.skow.lab5.ui.set_image

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import androidx.viewpager2.widget.ViewPager2
import com.skow.lab5.databinding.ImageFragmentSetBinding
import com.skow.lab5.ui.home.HomeViewModel
import com.skow.lab5.ui.set_image.repository.GalleryDataItem
import com.skow.lab5.ui.set_image.repository.GalleryRepository

class ImageFragmentSet : Fragment() {
    private lateinit var binding: ImageFragmentSetBinding
    private lateinit var imageRepo: GalleryRepository
    private lateinit var listItem: MutableList<GalleryDataItem>
    private lateinit var vPager: ViewPager2
    private val sharedViewModel: HomeViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageRepo = GalleryRepository.getinstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ImageFragmentSetBinding.inflate(inflater, container, false)

        listItem = imageRepo.getSharedList()

        val vpAdapter = CustomPagerAdapter(this.requireActivity(), listItem)
        vPager = binding.viewPager
        vPager.adapter = vpAdapter

        vPager.post {
            val index = arguments?.getInt("position")

            if (index != null){
                vPager.currentItem = index
            }else{
                vPager.currentItem = 0
            }
        }

        binding.backButtonSettings.setOnClickListener {
            sharedViewModel.imgId = MutableLiveData(vPager.currentItem)
            findNavController().navigateUp()
        }

        return binding.root
    }

    inner class CustomPagerAdapter(fa: FragmentActivity, private val imageList: MutableList<GalleryDataItem>): FragmentStateAdapter(fa) {

        override fun createFragment(position: Int): Fragment {
            return ImageFragment.newInstance(imageList[position].curi!!)
        }

        override fun onBindViewHolder(
            holder: FragmentViewHolder,
            position: Int,
            payloads: MutableList<Any>
        ) {
            super.onBindViewHolder(holder, position, payloads)
        }

        override fun getItemCount(): Int {
            return imageList.size
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ImageFragmentSet().apply {
                arguments = Bundle().apply {
                }
            }
    }
}