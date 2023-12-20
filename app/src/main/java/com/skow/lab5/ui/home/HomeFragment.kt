package com.skow.lab5.ui.home

import android.os.Bundle
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.skow.lab5.R
import com.skow.lab5.databinding.FragmentHomeBinding
import com.skow.lab5.ui.set_image.GalleryViewModel

class HomeFragment : Fragment() {

    private val sharedViewModel: HomeViewModel by activityViewModels()
    private val otherViewModel: GalleryViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sharedViewModel.hello.observe(viewLifecycleOwner) {
                newText -> binding.textHello.text = newText
        }

        sharedViewModel.desc.observe(viewLifecycleOwner) {
            newText -> binding.textDesc.text = newText
        }

        sharedViewModel.imgId.observe(viewLifecycleOwner) { imgId ->
            when (imgId) {
                R.drawable.kai -> {
                    binding.imageHome.setImageResource(imgId)
                }
                else -> {
                    val imageUri = otherViewModel.getGalleryData(requireContext())[imgId].curi
                    Glide.with(this)
                        .load(imageUri)
                        .into(binding.imageHome)
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}