package com.ninanok.githubusersearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ninanok.githubusersearch.adapter.LoadPageStateAdapter
import com.ninanok.githubusersearch.adapter.UserSearchListAdapter
import com.ninanok.githubusersearch.support.SearchUserPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    @Inject lateinit var userListAdapter: UserSearchListAdapter
    @Inject lateinit var loadStateAdapter: LoadPageStateAdapter
    @Inject lateinit var searchUserPagingSource: SearchUserPagingSource

    private val flow = Pager(PagingConfig(pageSize = 5)){
        searchUserPagingSource
    }.flow

    suspend fun showSearchResult(keyword: String) {
        userListAdapter.submitData(PagingData.empty())
        searchUserPagingSource.keyword = keyword
        flow.collectLatest { pagingData ->
            userListAdapter.submitData(pagingData)
        }
    }
}