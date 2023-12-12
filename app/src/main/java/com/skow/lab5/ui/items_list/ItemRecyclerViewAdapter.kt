//package com.skow.lab5.ui.items_list
//
//import android.annotation.SuppressLint
//import android.app.AlertDialog
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.fragment.app.FragmentManager
//import androidx.recyclerview.widget.RecyclerView
//import com.skow.lab5.MainActivity
//import com.skow.lab5.R
//import com.skow.lab5.databinding.FragmentItemBinding
//import com.skow.lab5.ui.items_list.placeholder.ItemEntity
//
//class ItemRecyclerViewAdapter(
//    private var values: List<ItemEntity>,
//    private val fragmentManager: FragmentManager,
//    private val activity: MainActivity
//)
//    : RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>(){
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): ViewHolder {
//        return ViewHolder(FragmentItemBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false))
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = values[position]
//        holder.idView.text = item.id.toString()
//        holder.contentView.text = item.name
//        when (item.gender){
//            "M" -> holder.imgView.setImageResource(R.drawable.male)
//            "K" -> holder.imgView.setImageResource(R.drawable.femenine)
//        }
//
//        holder.itemView.setOnClickListener {
////            val editItemFragment = EditItemFragment.newInstance(item)
//            val bundle = Bundle()
//            bundle.putParcelable("item", item)
//            activity.getController().navigate(R.id.nav_edit_item)
////            editItemFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_Lab5)
////
////            editItemFragment.show(fragmentManager, "EditItemFragment")
//        }
//
//        holder.itemView.setOnLongClickListener {
//            val builder = AlertDialog.Builder(activity)
//            builder.setTitle(R.string.confirm_deletion)
//            builder.setMessage(R.string.question_about_deletion)
//
//            builder.setPositiveButton(R.string.accept) { _, _ ->
//                val itemViewModel = activity.itemViewModel
//                itemViewModel.deleteItem(item)
//                notifyDataSetChanged()
//            }
//
//            builder.setNegativeButton(R.string.cancel, null)
//
//            val dialog = builder.create()
//            dialog.show()
//
//            true
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return values.size
//    }
//
//    inner class ViewHolder(binding: FragmentItemBinding): RecyclerView.ViewHolder(binding.root){
//        val idView: TextView = binding.itemNumber
//        val contentView: TextView = binding.content
//        val imgView: ImageView = binding.rowImage
//
//        override fun toString(): String {
//            return super.toString() + " '" + contentView.text + "'"
//        }
//
//    }
//}
