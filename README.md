# GithubUserSearch


Github API를 이용하여 Github 유저를 검색하고 클릭시 Dialog를 통해 레파지토리 리스트를 볼 수 있습니다.

MVVM 방식으로 hilt, paging3, retrofit, glide가 사용되었습니다.

Configuration
---------------------
local.properties 파일에 github token을 추가하여야 합니다.
```properties
github.token="<YOUR_GITHUB_TOKEN_HERE>"
```

Screen
---------------------
|Search|Repository|
|---|---|
|![search](https://user-images.githubusercontent.com/11027538/221418878-0e5f3981-ddce-4d32-b87b-620f1ac015d1.jpeg)|![repo](https://user-images.githubusercontent.com/11027538/221418872-4be7127e-8a0d-467f-b013-aee5d560eef9.jpeg)|


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
