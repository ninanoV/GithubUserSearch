package com.ninanok.githubusersearch.dialog

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.ninanok.githubusersearch.databinding.DialogReposBinding
import com.ninanok.githubusersearch.rest.response.data.UserDetail
import com.ninanok.githubusersearch.support.PagingErrorHandle
import com.ninanok.githubusersearch.viewmodel.RepoDialogViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RepoDialog(private val userDetail: UserDetail) : DialogFragment() {

    private var binding: DialogReposBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogReposBinding.inflate(inflater, container, false)
        Log.e("RepoDialog", "onCreateView")
        binding?.let { binding ->
            binding.userLogin = userDetail.login

            val viewModel = ViewModelProvider(requireActivity())[RepoDialogViewModel::class.java]

            binding.rvRepoList.adapter = viewModel.userRepositoryAdapter.withLoadStateFooter(viewModel.loadStateAdapter)

            viewModel.userRepositoryAdapter.addLoadStateListener { loadState ->
                // 최초 로딩 progressbar 표시
                if (loadState.source.refresh is LoadState.Loading) {
                    binding.pbRepoListLoading.visibility = View.VISIBLE
                } else {
                    binding.pbRepoListLoading.visibility = View.GONE
                }

                PagingErrorHandle.loadErrorHandle(requireContext(), loadState)
            }

            lifecycleScope.launch {
                viewModel.showSearchResult(userDetail.login)
            }
        }


        return binding!!.root
    }

    override fun onResume() {
        super.onResume()

        // 다이얼로그 너비를 화면 비율에 맞추어 조절
        dialog?.window?.apply {
            if(Build.VERSION.SDK_INT > 30) {
                val bounds = requireActivity().windowManager.currentWindowMetrics.bounds

                attributes.width = (bounds.width() * 0.9f).toInt()
            } else {
                val displayMetrics = DisplayMetrics()
                requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

                attributes.width = (displayMetrics.widthPixels * 0.9f).toInt()
            }

            // 다이얼로그가 화면 밖으로 벗어나는 것 방지
            setGravity(Gravity.CENTER)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        Log.e("RepoDialog", "onDestroy")
    }
}