package com.skow.lab5.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.Fragment
import com.skow.lab5.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val sharedViewModel: HomeViewModel by activityViewModels()
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

        sharedViewModel.imgId.observe(viewLifecycleOwner) {
            imgId -> binding.imageHome.setImageResource(imgId)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}