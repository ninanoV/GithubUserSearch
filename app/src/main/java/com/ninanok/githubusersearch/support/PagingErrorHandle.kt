package com.ninanok.githubusersearch.support

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

object PagingErrorHandle {

    // 로드 오류 시 토스트 표시
    fun loadErrorHandle(context: Context, loadState: CombinedLoadStates) {
        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error
            ?: loadState.append as? LoadState.Error
            ?: loadState.prepend as? LoadState.Error
            ?: loadState.refresh as? LoadState.Error
        errorState?.let {
            Log.e("PagingErrorHandle", Log.getStackTraceString(it.error))
            it.error.message?.let { errorMessage ->
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            } ?: run {
                Toast.makeText(context, "로딩 중 오류 발생", Toast.LENGTH_SHORT).show()
            }
        }
    }
}