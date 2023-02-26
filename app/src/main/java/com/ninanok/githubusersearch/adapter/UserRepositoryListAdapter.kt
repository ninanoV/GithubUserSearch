package com.ninanok.githubusersearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ninanok.githubusersearch.databinding.ListitemGithubRepoBinding
import com.ninanok.githubusersearch.rest.response.data.UserRepository
import javax.inject.Inject

class UserRepositoryListAdapter @Inject constructor()
    : PagingDataAdapter<UserRepository, UserRepositoryListHolder>(SectionComparator) {

    override fun onBindViewHolder(holder: UserRepositoryListHolder, position: Int) {
        getItem(position)?.let {
            holder.bindData(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepositoryListHolder {
        val binding = ListitemGithubRepoBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return UserRepositoryListHolder(binding)
    }


    object SectionComparator : DiffUtil.ItemCallback<UserRepository>() {
        override fun areItemsTheSame(oldItem: UserRepository, newItem: UserRepository): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserRepository, newItem: UserRepository): Boolean {
            return oldItem == newItem
        }
    }
}