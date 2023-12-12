package com.skow.lab5.ui.set_image

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.skow.lab5.databinding.FragmentPhotoBinding
import com.skow.lab5.ui.home.HomeViewModel

class FragmentImage(private val img: Int) : Fragment(){
    private var _binding: FragmentPhotoBinding? = null
    private val sharedViewModel: HomeViewModel by activityViewModels()
    private val binding get() = _binding!!
//    private val photos = arrayOf(R.drawable.kai, R.drawable.zane, R.drawable.jay, R.drawable.cole)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.photoImageView.setImageResource(img)
        sharedViewModel.imgId.value = img
        Log.i("img", sharedViewModel.imgId.value.toString())
    }

    override fun onResume() {
        super.onResume()

        sharedViewModel.imgId.value = img
        Log.i("img", sharedViewModel.imgId.value.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}