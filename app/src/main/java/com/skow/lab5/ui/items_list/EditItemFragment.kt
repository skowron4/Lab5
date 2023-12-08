package com.skow.lab5.ui.items_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
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

        if (item != null) {
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

        binding.radioButtonMale.setOnClickListener {
            binding.image.setImageResource(R.drawable.male)
        }

        binding.radioButtonFemale.setOnClickListener {
            binding.image.setImageResource(R.drawable.femenine)
        }


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
            dismiss()
        }

        binding.saveButton.setOnClickListener {
            val newItem = Placeholder.PlaceholderItem(
                id = item?.id!!,
                name = binding.editTextName.text.toString(),
                power = binding.textViewStrengthValue.text.toString().toFloat(),
                gender = if (binding.radioButtonMale.isChecked) "M" else "K",
                desc = binding.editTextDescription.text.toString())

            val itemViewModel = (requireActivity() as MainActivity).itemViewModel
            itemViewModel.updateItem(newItem)
        }

        binding.deleteButton.setOnClickListener {
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
