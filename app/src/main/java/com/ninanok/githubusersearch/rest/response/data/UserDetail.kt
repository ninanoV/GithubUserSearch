package com.ninanok.githubusersearch.rest.response.data

data class UserDetail(
    val login: String = "",
    val id: Int = -1,
    val avatar_url: String = "",
    val repos_url: String = "",
    val url: String = "",
    val name: String? = null,
    val location: String? = null,
    val email: String? = null,
    val bio: String? = null
) : BindingUtil()