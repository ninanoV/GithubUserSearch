package com.ninanok.githubusersearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.ninanok.githubusersearch.databinding.ListitemGithubUserBinding
import com.ninanok.githubusersearch.rest.response.data.UserDetail
import javax.inject.Inject

class UserSearchListAdapter @Inject constructor()
    : PagingDataAdapter<UserDetail, UserSearchListHolder>(SectionComparator) {

    override fun onBindViewHolder(holder: UserSearchListHolder, position: Int) {
        getItem(position)?.let {
            holder.bindData(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSearchListHolder {
        val binding = ListitemGithubUserBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return UserSearchListHolder(binding)
    }


    object SectionComparator : DiffUtil.ItemCallback<UserDetail>() {
        override fun areItemsTheSame(oldItem: UserDetail, newItem: UserDetail): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserDetail, newItem: UserDetail): Boolean {
            return oldItem == newItem
        }
    }
}