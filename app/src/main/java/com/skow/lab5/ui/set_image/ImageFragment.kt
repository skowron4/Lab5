package com.skow.lab5.ui.set_image

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.skow.lab5.databinding.FragmentImageBinding

private const val IMAGE_URI = "imageUri"
class ImageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var imageUri: Uri? = null
    private lateinit var binding: FragmentImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageUri = it.getParcelable(IMAGE_URI) as Uri?
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val imageViewSettings = binding.ImageViewSettings

        Glide.with(this)
            .load(imageUri)
            .into(imageViewSettings)

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(imageUri: Uri) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(IMAGE_URI, imageUri)
                }
            }
    }
}