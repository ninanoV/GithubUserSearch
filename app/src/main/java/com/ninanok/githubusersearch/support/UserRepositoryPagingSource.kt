package com.ninanok.githubusersearch.support

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ninanok.githubusersearch.rest.RetrofitService
import com.ninanok.githubusersearch.rest.response.data.UserRepository
import javax.inject.Inject

class UserRepositoryPagingSource @Inject constructor(private val backend: RetrofitService):
    PagingSource<Int, UserRepository>() {

    lateinit var userLogin: String

    override fun getRefreshKey(state: PagingState<Int, UserRepository>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserRepository> {
        return try {
            val nextPageNumber = params.key ?: 1
            val repoResponse = backend.getUserRepos(userLogin, nextPageNumber)

            val nextKey = if (repoResponse.isNotEmpty()) nextPageNumber + 1 else null


            LoadResult.Page(
                data = repoResponse,
                prevKey = null,
                nextKey = nextKey
            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}