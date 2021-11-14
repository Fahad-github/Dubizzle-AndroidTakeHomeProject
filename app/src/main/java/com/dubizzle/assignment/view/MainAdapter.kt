package com.dubizzle.assignment.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dubizzle.assignment.R
import com.dubizzle.assignment.common.setImageUrl
import com.dubizzle.assignment.databinding.ItemLayoutBinding
import com.dubizzle.assignment.listener.ItemClickListener
import com.dubizzle.core.model.ItemModel

class MainAdapter : RecyclerView.Adapter<MainAdapter.ItemViewHolder>() {

    var itemList: List<ItemModel> = ArrayList()
    var itemClickListener: ItemClickListener? = null

    inner class ItemViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_layout,
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.itemView.setOnClickListener {
                itemClickListener?.onItemClicked(itemList[adapterPosition])
            }
        }

        fun setData(item: ItemModel) {
            binding.textViewName.text = item.name
            binding.textViewPrice.text = item.price
            binding.imageViewItem.setImageUrl(item.image_urls_thumbnails[0])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setData(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    fun setList(items: List<ItemModel>) {
        this.itemList = items
        notifyDataSetChanged()
    }

}