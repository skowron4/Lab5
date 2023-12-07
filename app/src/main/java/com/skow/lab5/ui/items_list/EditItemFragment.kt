package com.skow.lab5.ui.items_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.skow.lab5.MainActivity
import com.skow.lab5.R
import com.skow.lab5.databinding.FragmentEditItemBinding
import com.skow.lab5.ui.items_list.placeholder.Placeholder

class EditItemFragment : DialogFragment() {

    private var _binding: FragmentEditItemBinding? = null
    private val binding get() = _binding!!
    private var item: Placeholder.PlaceholderItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        item = arguments?.getParcelable(ARG_ITEM)
        val seekBar: SeekBar = binding.seekBarStrength
        val textViewStrengthValue: TextView = binding.textViewStrengthValue
        val cancelButton: Button = binding.cancelButton
        val saveButton: Button = binding.saveButton
        val maleButton: RadioButton = binding.radioButtonMale
        val femaleButton: RadioButton = binding.radioButtonFemale
        val deleteButton: Button = binding.deleteButton

        if (item != null) {
            binding.editTextName.setText(item?.name)
            binding.editTextDescription.setText(item?.desc)
            binding.seekBarStrength.progress = item?.power?.toInt()!! * 5
            binding.textViewStrengthValue.text = item?.power.toString()

            when (item?.gender) {
                "M" -> {
                    binding.image.setImageResource(R.drawable.male)
                    maleButton.isChecked = true
                }
                "K" -> {
                    binding.image.setImageResource(R.drawable.femenine)
                    femaleButton.isChecked = true
                }
            }
        }

        maleButton.setOnClickListener {
            binding.image.setImageResource(R.drawable.male)
        }

        femaleButton.setOnClickListener {
            binding.image.setImageResource(R.drawable.femenine)
        }


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
                id = item?.id!!,
                name = binding.editTextName.text.toString(),
                power = textViewStrengthValue.text.toString().toFloat(),
                gender = if (binding.radioButtonMale.isChecked) "M" else "K",
                desc = binding.editTextDescription.text.toString())

            val itemViewModel = (requireActivity() as MainActivity).itemViewModel
            itemViewModel.updateItem(newItem)
        }

        deleteButton.setOnClickListener {
            val itemViewModel = (requireActivity() as MainActivity).itemViewModel
            itemViewModel.deleteItem(item)
            dismiss()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_ITEM = "item"

        fun newInstance(item: Placeholder.PlaceholderItem): EditItemFragment {
            val fragment = EditItemFragment()
            val args = Bundle()
            args.putParcelable(ARG_ITEM, item)
            fragment.arguments = args
            return fragment
        }
    }
}
