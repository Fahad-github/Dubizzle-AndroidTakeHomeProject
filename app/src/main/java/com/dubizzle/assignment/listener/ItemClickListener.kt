package com.dubizzle.assignment.listener

import com.dubizzle.core.model.ItemModel

interface ItemClickListener {
    fun onItemClicked(item:ItemModel)
}