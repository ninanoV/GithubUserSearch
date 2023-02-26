package com.ninanok.githubusersearch.rest.response

data class SearchResponse<T>(
    val total_count: Int,
    val items: List<T>
)
