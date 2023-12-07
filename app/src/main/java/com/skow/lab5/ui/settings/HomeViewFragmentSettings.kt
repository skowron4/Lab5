package com.skow.lab5.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.skow.lab5.R
import com.skow.lab5.databinding.FragmentSettingsBinding
import com.skow.lab5.ui.home.HomeViewModel


class HomeViewFragmentSettings : Fragment() {
    private val sharedViewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSettingsBinding.bind(view)

        binding.button.setOnClickListener{
            sharedViewModel.hello.value = binding.editTextNewUser.text.toString()
            sharedViewModel.desc.value = binding.editTextDesc.text.toString()

            when (binding.radioGroupImages.checkedRadioButtonId){
                binding.radioButtonImage1.id -> sharedViewModel.imgId.value = R.drawable.kai
                binding.radioButtonImage2.id -> sharedViewModel.imgId.value = R.drawable.zane
                binding.radioButtonImage3.id -> sharedViewModel.imgId.value = R.drawable.jay
                binding.radioButtonImage4.id -> sharedViewModel.imgId.value = R.drawable.cole
            }

            Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onResume() {
        super.onResume()
        binding.editTextNewUser.setText(sharedViewModel.hello.value)
        binding.editTextDesc.setText(sharedViewModel.desc.value)
        when (sharedViewModel.imgId.value) {
            R.drawable.kai -> binding.radioButtonImage1.isChecked = true
            R.drawable.zane -> binding.radioButtonImage2.isChecked = true
            R.drawable.jay -> binding.radioButtonImage3.isChecked = true
            R.drawable.cole -> binding.radioButtonImage4.isChecked = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}