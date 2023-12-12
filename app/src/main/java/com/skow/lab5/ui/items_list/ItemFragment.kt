package com.skow.lab5.ui.items_list

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.skow.lab5.MainActivity
import com.skow.lab5.R
import com.skow.lab5.databinding.FragmentItemBinding
import com.skow.lab5.ui.items_list.placeholder.ItemEntity
import com.skow.lab5.ui.items_list.placeholder.Repository


class ItemFragment : Fragment() {

    private var columnCount = 1
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        if (view is CoordinatorLayout) {
            val recyclerView: RecyclerView? = view.findViewById(R.id.list)

            if (recyclerView != null) {
                with(recyclerView) {
                    layoutManager = when {
                        columnCount <= 1 -> LinearLayoutManager(context)
                        else -> GridLayoutManager(context, columnCount)
                    }

                    adapter = ItemRecyclerViewAdapter(
                        Repository.getInstance(requireContext()).getData())
                }
            }

//            childFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

            val fab: FloatingActionButton = view.findViewById(R.id.fab)

            fab.setOnClickListener {
                findNavController().navigate(R.id.nav_add_item)

//                addItemFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_Lab5)
//
//                addItemFragment.show(requireActivity().supportFragmentManager, "AddItemFragment")
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewModel = (requireActivity() as MainActivity).itemViewModel

        itemViewModel.items.observe(viewLifecycleOwner, Observer { items ->
            val recyclerView: RecyclerView? = view.findViewById(R.id.list)
            recyclerView?.adapter = ItemRecyclerViewAdapter(
                items)
        })
    }

    override fun onResume() {
        super.onResume()
        val recyclerView: RecyclerView? = view?.findViewById(R.id.list)
        recyclerView?.adapter?.notifyItemInserted(itemViewModel.items.value!!.size)
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    inner class ItemRecyclerViewAdapter(
        private var values: List<ItemEntity>)
        : RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>(){
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            return ViewHolder(
                FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = item.id.toString()
            holder.contentView.text = item.name
            when (item.gender){
                "M" -> holder.imgView.setImageResource(R.drawable.male)
                "K" -> holder.imgView.setImageResource(R.drawable.femenine)
            }

            holder.itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("item", item.id)
                findNavController().navigate(R.id.nav_details_item, bundle)
            }

            holder.itemView.setOnLongClickListener {
                val builder = AlertDialog.Builder(activity)
                builder.setTitle(R.string.confirm_deletion)
                builder.setMessage(R.string.question_about_deletion)

                builder.setPositiveButton(R.string.accept) { _, _ ->
                    itemViewModel.deleteItem(item)
                }

                builder.setNegativeButton(R.string.cancel, null)

                val dialog = builder.create()
                dialog.show()

                true
            }
        }

        override fun getItemCount(): Int {
            return values.size
        }

        inner class ViewHolder(binding: FragmentItemBinding): RecyclerView.ViewHolder(binding.root){
            val idView: TextView = binding.itemNumber
            val contentView: TextView = binding.content
            val imgView: ImageView = binding.rowImage

            override fun toString(): String {
                return super.toString() + " '" + contentView.text + "'"
            }

        }
    }

}