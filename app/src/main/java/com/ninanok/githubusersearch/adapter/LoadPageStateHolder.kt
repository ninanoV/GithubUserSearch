package com.ninanok.githubusersearch.adapter

import android.view.View
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.ninanok.githubusersearch.databinding.ListitemProgressBinding

class LoadPageStateHolder(private val binding: ListitemProgressBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        when (loadState) {
            is LoadState.Loading -> {
                binding.itemProgressbar.visibility = View.VISIBLE
            }
            is LoadState.Error -> {
                binding.itemProgressbar.visibility = View.GONE
            }
            is LoadState.NotLoading -> {
                binding.itemProgressbar.visibility = View.GONE
            }
        }
    }
}