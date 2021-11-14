package com.dubizzle.assignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dubizzle.assignment.R
import com.dubizzle.assignment.base.BaseActivity
import com.dubizzle.assignment.databinding.ActivityDetailsBinding
import com.dubizzle.core.model.ItemModel

class DetailsActivity : BaseActivity() {

    private val binding: ActivityDetailsBinding by binding(R.layout.activity_details)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val item = intent.getSerializableExtra("item") as ItemModel
        setData(item)

    }

    private fun setData(item: ItemModel) {
        val sliderAdapter = ImageSliderAdapter()
        sliderAdapter.imageUrlsList = item.image_urls
        binding.recyclerViewImages.apply {
            layoutManager = LinearLayoutManager(
                    this@DetailsActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            adapter = sliderAdapter
        }
        binding.textViewNameValue.text = item.name
        binding.textViewPriceValue.text = item.price
    }
}