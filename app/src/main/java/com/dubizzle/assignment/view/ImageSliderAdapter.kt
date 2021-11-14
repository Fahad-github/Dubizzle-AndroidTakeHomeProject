package com.dubizzle.assignment.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dubizzle.assignment.R
import com.dubizzle.assignment.common.setImageUrl
import com.dubizzle.assignment.databinding.ItemSliderBinding

class ImageSliderAdapter : RecyclerView.Adapter<ImageSliderAdapter.ItemViewHolder>() {

    var imageUrlsList: List<String> = ArrayList()

    inner class ItemViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemSliderBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_slider,
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(imageUrl: String) {
            binding.imageViewItem.setImageUrl(imageUrl)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setData(imageUrlsList[position])
    }

    override fun getItemCount(): Int {
        return imageUrlsList.size
    }


    fun setList(items: List<String>) {
        this.imageUrlsList = items
        notifyDataSetChanged()
    }

}