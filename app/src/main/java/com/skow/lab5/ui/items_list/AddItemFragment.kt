package com.skow.lab5.ui.items_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.skow.lab5.MainActivity
import com.skow.lab5.databinding.FragmentAddItemBinding
import com.skow.lab5.ui.items_list.placeholder.Placeholder

class AddItemFragment : DialogFragment() {

    private var _binding: FragmentAddItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val seekBar: SeekBar = binding.seekBarStrength
        val textViewStrengthValue: TextView = binding.textViewStrengthValue
        val cancelButton: Button = binding.cancelButton
        val saveButton: Button = binding.saveButton

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textViewStrengthValue.text = (progress / 5.0f).toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                // TODO: Możesz dodać swoją implementację
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                // TODO: Możesz dodać swoją implementację
            }
        })

        cancelButton.setOnClickListener {
            dismiss()
        }

        saveButton.setOnClickListener {
            val newItem = Placeholder.PlaceholderItem(
                id = Placeholder.nextIndex(),
                name = binding.editTextName.text.toString(),
                power = textViewStrengthValue.text.toString().toFloat(),
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
