package com.skow.lab5.ui.items_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.skow.lab5.MainActivity
import com.skow.lab5.R
import com.skow.lab5.databinding.FragmentDetailsItemBinding
import com.skow.lab5.ui.items_list.placeholder.ItemEntity

class DetailsItemFragment : Fragment() {
    private var _binding: FragmentDetailsItemBinding? = null
    private val binding get() = _binding!!
    private var item: ItemEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemId = arguments?.getInt("item")

        binding.seekBarStrength.isEnabled = false

        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.modifyButton.setOnClickListener {
            val bundle = Bundle()
            if (item != null) {
                bundle.putInt("item", itemId!!)
                findNavController().navigate(R.id.nav_edit_item, bundle)
            }else {
                Toast.makeText(requireContext(), "Item nie istnieje", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val itemId = arguments?.getInt("item")

        try {
            if (itemId != null){
                item = (requireActivity() as MainActivity).itemViewModel.getItem(itemId)
                binding.editTextName.setText(item?.name)
                binding.editTextDescription.setText(item?.desc)
                binding.seekBarStrength.progress = item?.power?.toInt()!! * 5
                binding.textViewStrengthValue.text = item?.power.toString()

                when (item?.gender) {
                    "M" -> {
                        binding.image.setImageResource(R.drawable.male)
                        binding.radioButtonMale.isChecked = true
                    }
                    "K" -> {
                        binding.image.setImageResource(R.drawable.femenine)
                        binding.radioButtonFemale.isChecked = true
                    }
                }
            }
        } catch (e: Exception){
            binding.seekBarStrength.progress = 0
            binding.radioButtonFemale.isChecked = false
            binding.radioButtonMale.isChecked = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}