package com.skow.lab5.ui.items_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.skow.lab5.MainActivity
import com.skow.lab5.R
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
                        Repository.getInstance(requireContext()).getData(),
                        childFragmentManager,
                        (requireActivity() as MainActivity))
                }

            }

            childFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

            val fab: FloatingActionButton = view.findViewById(R.id.fab)

            fab.setOnClickListener {
                val addItemFragment = AddItemFragment()

                addItemFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_Lab5)

                addItemFragment.show(requireActivity().supportFragmentManager, "AddItemFragment")
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
                items,
                childFragmentManager,
                (requireActivity() as MainActivity))
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        val recyclerView: RecyclerView? = view?.findViewById(R.id.list)
        recyclerView?.adapter?.notifyDataSetChanged()
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
}