package com.skow.lab5.ui.set_image

import android.content.ActivityNotFoundException
import android.content.ContentUris
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.core.content.FileProvider
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skow.lab5.R
import com.skow.lab5.databinding.FragmentGalleryBinding
import com.skow.lab5.databinding.ImageGalleryCardBinding
import com.skow.lab5.ui.items_list.ItemFragment.Companion.ARG_COLUMN_COUNT
import com.skow.lab5.ui.set_image.repository.GalleryDataItem
import java.io.File
import java.util.Date

class GalleryFragment : Fragment() {

    private val viewModel: GalleryViewModel by activityViewModels { GalleryViewModel.Factory }
    private lateinit var binding: FragmentGalleryBinding
    private lateinit var lastFile: File
    private var columnCount = 2

    companion object {
        fun newInstance() = GalleryFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateImages(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)

        val galAdapter = GalleryPhotoAdapter(requireContext(), findNavController())


        when{
            columnCount <= 1 -> binding.galleryRecyclerView.layoutManager = LinearLayoutManager(context)
            else -> binding.galleryRecyclerView.layoutManager = GridLayoutManager(context, 2)
        }
        binding.galleryRecyclerView.adapter = galAdapter

        viewModel.repo.observe(viewLifecycleOwner) {
            galAdapter.submitList(viewModel.repo.value)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = GalleryPhotoAdapter(requireContext(), findNavController())

        val photoLuncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { result: Boolean ->
            if (result) {
                adapter.submitList(viewModel.getGalleryData(requireContext()))
                binding.galleryRecyclerView.adapter = adapter

                Toast.makeText(requireContext(), "Photo taken", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(requireContext(),"Photo no taken",Toast.LENGTH_LONG).show()
            }
        }

        binding.camera.setOnClickListener{
            val lastFileUri = getNewFileUri()
            try {
                photoLuncher.launch(lastFileUri)
            } catch (e: ActivityNotFoundException){
                Toast.makeText(requireContext(),"Camera doesn't work",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getNewFileUri(): Uri {
        val tStamp: String = android.icu.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

        val dir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "AMPA")
        dir.mkdirs()

        val tmpFile = File.createTempFile("Photo_" + "${tStamp}", ".jpg", dir)
        lastFile = tmpFile

        return FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            tmpFile
        )
    }

    class GalleryPhotoAdapter(
        val appContext: Context,
        val navController: NavController
    ) : ListAdapter<GalleryDataItem, GalleryPhotoAdapter.GalleryViewHolder>(DiffCallback) {

        inner class GalleryViewHolder(viewBinding: ImageGalleryCardBinding) :
            RecyclerView.ViewHolder(viewBinding.root) {
            val cardView: CardView = viewBinding.imageCardview
            val imageView: ImageView = viewBinding.imageView
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
            val viewBinding =
                ImageGalleryCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return GalleryViewHolder(viewBinding)
        }

        @RequiresApi(Build.VERSION_CODES.Q)
        override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
            val currItem = getItem(position)

            currItem.curi?.let {
                val bitmap = MediaStore.Images.Thumbnails.getThumbnail(
                    appContext.contentResolver,
                    ContentUris.parseId(it),
                    MediaStore.Images.Thumbnails.MINI_KIND,
                    null
                )

                holder.imageView.setImageBitmap(bitmap)
            }

            holder.itemView.setOnClickListener {
                navController.navigate(R.id.nav_img_set, bundleOf(Pair("position", position)))
            }
        }

        companion object {
            private val DiffCallback = object : DiffUtil.ItemCallback<GalleryDataItem>() {
                override fun areItemsTheSame(
                    oldItem: GalleryDataItem,
                    newItem: GalleryDataItem
                ): Boolean {
                    return oldItem.curi == newItem.curi
                }

                override fun areContentsTheSame(
                    oldItem: GalleryDataItem,
                    newItem: GalleryDataItem
                ): Boolean {
                    return oldItem == newItem
                }
            }
        }
    }

}