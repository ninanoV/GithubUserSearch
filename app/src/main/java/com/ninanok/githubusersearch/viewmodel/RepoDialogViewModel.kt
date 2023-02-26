package com.ninanok.githubusersearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ninanok.githubusersearch.adapter.LoadPageStateAdapter
import com.ninanok.githubusersearch.adapter.UserRepositoryListAdapter
import com.ninanok.githubusersearch.support.UserRepositoryPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class RepoDialogViewModel @Inject constructor(): ViewModel() {

    @Inject lateinit var userRepositoryAdapter: UserRepositoryListAdapter
    @Inject lateinit var loadStateAdapter: LoadPageStateAdapter
    @Inject lateinit var userRepoPagingSource: UserRepositoryPagingSource

    private val flow = Pager(PagingConfig(pageSize = 5)){
        userRepoPagingSource
    }.flow

    suspend fun showSearchResult(userLogin: String) {
        userRepositoryAdapter.submitData(PagingData.empty())
        userRepoPagingSource.userLogin = userLogin
        flow.collectLatest { pagingData ->
            userRepositoryAdapter.submitData(pagingData)
        }
    }
}