package com.skow.lab5.ui.items_list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.skow.lab5.R
import com.skow.lab5.databinding.FragmentItemBinding
import com.skow.lab5.ui.items_list.placeholder.Placeholder.PlaceholderItem

class ItemRecyclerViewAdapter(
    private val values: List<PlaceholderItem>,
    private val fragmentManager: FragmentManager)
    : RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(FragmentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.name
        when (item.gender){
            "M" -> holder.imgView.setImageResource(R.drawable.male)
            "K" -> holder.imgView.setImageResource(R.drawable.femenine)
        }

        holder.itemView.setOnClickListener {
            val editItemFragment = EditItemFragment.newInstance(item)

            editItemFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_Lab5)

            editItemFragment.show(fragmentManager, "EditItemFragment")
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding): RecyclerView.ViewHolder(binding.root){
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        val imgView: ImageView = binding.rowImage

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }

    }
}
