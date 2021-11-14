package com.dubizzle.assignment.view

import android.os.Bundle
import android.telecom.Call
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dubizzle.assignment.R
import com.dubizzle.assignment.base.BaseActivity
import com.dubizzle.assignment.common.launchActivity
import com.dubizzle.assignment.common.showToast
import com.dubizzle.assignment.databinding.ActivityMainBinding
import com.dubizzle.assignment.listener.ItemClickListener
import com.dubizzle.core.model.ItemModel
import com.dubizzle.core.model.NetworkResult

class MainActivity : BaseActivity() , ItemClickListener{

    private val binding : ActivityMainBinding by binding(R.layout.activity_main)
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        populateList()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.getItemLiveData.observe(this,{response->
            when(response){
                is NetworkResult.Success->{
                    displayProgressBar(false)
                    populateRecyclerView(response.data.results)
                }
                is NetworkResult.Error -> {
                    displayProgressBar(false)
                    showToast(response.exception.message ?: "No response from server")
                }
            }

        })
    }

    private fun populateRecyclerView(results: List<ItemModel>) {
        val itemsAdapter = MainAdapter()
        itemsAdapter.itemClickListener = this
        itemsAdapter.setList(results)
        binding.recyclerViewItems.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = itemsAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun populateList() {
        displayProgressBar(true)
        viewModel.getItems()
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    override fun onItemClicked(item: ItemModel) {
        launchActivity<DetailsActivity> {
            putExtra("item",item)
        }
    }
}