package com.ninanok.githubusersearch.rest.response.data

data class UserRepository(
    val id: Int,
    val name: String,
    val owner: SearchUser,
    val description: String,
    val url: String,
    val stargazers_count: Int,
    val language: String
) : BindingUtil()
