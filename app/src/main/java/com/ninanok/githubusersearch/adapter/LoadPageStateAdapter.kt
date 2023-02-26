package com.ninanok.githubusersearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.ninanok.githubusersearch.databinding.ListitemProgressBinding
import javax.inject.Inject

class LoadPageStateAdapter @Inject constructor() : LoadStateAdapter<LoadPageStateHolder>() {
    override fun onBindViewHolder(holder: LoadPageStateHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadPageStateHolder {
        val binding = ListitemProgressBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return LoadPageStateHolder(binding)
    }

}


