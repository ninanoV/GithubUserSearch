package com.ninanok.githubusersearch.support

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ninanok.githubusersearch.rest.RetrofitService
import com.ninanok.githubusersearch.rest.response.data.UserDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import javax.inject.Inject
import kotlin.coroutines.coroutineContext


class SearchUserPagingSource @Inject constructor(private val backend: RetrofitService):
    PagingSource<Int, UserDetail>() {

    var keyword: String? = null

    override fun getRefreshKey(state: PagingState<Int, UserDetail>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDetail> {
        return try {
            val nextPageNumber = params.key ?: 1
            val searchResponse = if(!keyword.isNullOrBlank()) {
                backend.searchUsers("$keyword in:login", nextPageNumber)
            } else { null }

            searchResponse?.let { response ->

                val userDetails = mutableListOf<UserDetail>()

                // search 만으로는 유저의 정보가 부족하여 상세정보 비동기 요청
                val profileDeferredList = response.items.map {
                    CoroutineScope(coroutineContext).async {
                        try {
                            backend.getUserDetail(it.login)
                        } catch (e: Exception) {
                            UserDetail()
                        }
                    }
                }
                profileDeferredList.awaitAll().forEachIndexed { index, userDetail ->
                    if(userDetail.id > 0) {
                        userDetails.add(index, userDetail)
                    }
                }

                val nextKey = if (userDetails.size > 0) nextPageNumber + 1 else null

                LoadResult.Page(
                    data = userDetails.toList(),
                    prevKey = null,
                    nextKey = nextKey
                )
            } ?: run {
                Log.e("SearchUserPagingSource", "search response empty")
                LoadResult.Error(Exception("search response empty"))
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}