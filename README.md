# GithubUserSearch


Github API를 이용하여 Github 유저를 검색하고 클릭시 Dialog를 통해 레파지토리 리스트를 볼 수 있습니다.

Configuration
---------------------
local.properties 파일에 github token을 추가하여야 합니다.
```properties
github.token="<YOUR_GITHUB_TOKEN_HERE>"
```
Project File Tree
---------------------
```bash
.
├── Consts.kt
├── GithubUserSearchApplication.kt
├── activity
│   └── MainActivity.kt
├── adapter
│   ├── LoadPageStateAdapter.kt
│   ├── LoadPageStateHolder.kt
│   ├── UserRepositoryListAdapter.kt
│   ├── UserRepositoryListHolder.kt
│   ├── UserSearchListAdapter.kt
│   └── UserSearchListHolder.kt
├── dialog
│   └── RepoDialog.kt
├── rest
│   ├── RetrofitModule.kt
│   ├── RetrofitService.kt
│   └── response
│       ├── SearchResponse.kt
│       └── data
│           ├── BindingUtil.kt
│           ├── SearchUser.kt
│           ├── UserDetail.kt
│           └── UserRepository.kt
├── support
│   ├── BindingAdapter.kt
│   ├── PagingErrorHandle.kt
│   ├── SearchUserPagingSource.kt
│   └── UserRepositoryPagingSource.kt
└── viewmodel
    ├── MainViewModel.kt
    └── RepoDialogViewModel.kt

```