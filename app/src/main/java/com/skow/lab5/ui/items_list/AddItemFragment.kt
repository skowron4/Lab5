package com.skow.lab5.ui.items_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.skow.lab5.MainActivity
import com.skow.lab5.databinding.FragmentAddItemBinding
import com.skow.lab5.ui.items_list.placeholder.ItemEntity

class AddItemFragment : DialogFragment() {

    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.seekBarStrength.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                binding.textViewStrengthValue.text = (progress / 5.0f).toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                // TODO: Możesz dodać swoją implementację
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                // TODO: Możesz dodać swoją implementację
            }
        })

        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.saveButton.setOnClickListener {
            val newItem = ItemEntity(
                name = binding.editTextName.text.toString(),
                power = binding.textViewStrengthValue.text.toString().toFloat(),
                gender = if (binding.radioButtonMale.isChecked) "M" else "K",
                desc = binding.editTextDescription.text.toString())

            val itemViewModel = (requireActivity() as MainActivity).itemViewModel
            itemViewModel.addItem(newItem)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
