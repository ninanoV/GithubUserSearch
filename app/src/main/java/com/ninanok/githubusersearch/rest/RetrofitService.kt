package com.ninanok.githubusersearch.rest

import com.ninanok.githubusersearch.rest.response.SearchResponse
import com.ninanok.githubusersearch.rest.response.data.SearchUser
import com.ninanok.githubusersearch.rest.response.data.UserDetail
import com.ninanok.githubusersearch.rest.response.data.UserRepository
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("/search/users")
    suspend fun searchUsers(@Query("q") query: String, @Query("page") page: Int): SearchResponse<SearchUser>

    @GET("/users/{userLoginId}")
    suspend fun getUserDetail(@Path("userLoginId") userLoginId: String): UserDetail

    @GET("/users/{userLoginId}/repos")
    suspend fun getUserRepos(@Path("userLoginId") userLoginId: String, @Query("page") page: Int): List<UserRepository>
}