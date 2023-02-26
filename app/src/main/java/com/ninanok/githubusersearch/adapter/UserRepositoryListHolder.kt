package com.ninanok.githubusersearch.adapter

import androidx.recyclerview.widget.RecyclerView
import com.ninanok.githubusersearch.databinding.ListitemGithubRepoBinding
import com.ninanok.githubusersearch.rest.response.data.UserRepository

class UserRepositoryListHolder(private val binding: ListitemGithubRepoBinding)
    : RecyclerView.ViewHolder(binding.root) {

        fun bindData(userRepository: UserRepository) {
            binding.repo = userRepository
        }

}