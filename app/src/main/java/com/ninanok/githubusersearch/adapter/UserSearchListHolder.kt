package com.ninanok.githubusersearch.adapter

import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.ninanok.githubusersearch.databinding.ListitemGithubUserBinding
import com.ninanok.githubusersearch.dialog.RepoDialog
import com.ninanok.githubusersearch.rest.response.data.UserDetail

class UserSearchListHolder(private val binding: ListitemGithubUserBinding)
    : RecyclerView.ViewHolder(binding.root) {

        fun bindData(userDetail: UserDetail) {
            binding.user = userDetail

            binding.root.setOnClickListener {
                val dialog = RepoDialog(userDetail)
                dialog.isCancelable = true
                dialog.show((it.context as FragmentActivity).supportFragmentManager, "repoDialog")
            }
        }

}