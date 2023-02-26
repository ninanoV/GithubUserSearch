package com.ninanok.githubusersearch.activity

import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.ninanok.githubusersearch.R
import com.ninanok.githubusersearch.databinding.ActivityMainBinding
import com.ninanok.githubusersearch.support.PagingErrorHandle
import com.ninanok.githubusersearch.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel: MainViewModel by viewModels()

        binding.rvUserList.adapter = viewModel.userListAdapter.withLoadStateFooter(viewModel.loadStateAdapter)

        viewModel.userListAdapter.addLoadStateListener { loadState ->

            // 최초 로딩 progressbar 표시
            if (loadState.source.refresh is LoadState.Loading) {
                binding.pbUserListLoading.visibility = View.VISIBLE
            } else {
                binding.pbUserListLoading.visibility = View.GONE
            }

            PagingErrorHandle.loadErrorHandle(this, loadState)
        }

        binding.etUserSearch.setOnKeyListener { view, keyCode, keyEvent ->
            if(keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                hideSoftKeyboard(view)
                lifecycleScope.launch {
                    viewModel.showSearchResult(binding.etUserSearch.text.toString())
                }
                true
            } else {
                false
            }
        }

        binding.ivUserSearchBtn.setOnClickListener {
            hideSoftKeyboard(binding.etUserSearch)
            lifecycleScope.launch {
                viewModel.showSearchResult(binding.etUserSearch.text.toString())
            }

        }

    }

    private fun hideSoftKeyboard(view: View) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}