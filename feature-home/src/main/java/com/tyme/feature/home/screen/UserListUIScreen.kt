package com.tyme.feature.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.tyme.component.R
import com.tyme.component.theme.GitAdminTheme
import com.tyme.component.ui.LoadMoreErrorItem
import com.tyme.component.ui.LoadingItem
import com.tyme.core.model.UserModel
import com.tyme.core.model.encodeUserUrl
import com.tyme.feature.home.screen.ui.UserItem
import com.tyme.feature.home.screen.ui.ViewTags
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UserListUIScreen(
    userPagingData: LazyPagingItems<UserModel>,
    onNavigateDetailScreen: ((UserModel) -> Unit),
    modifier: Modifier = Modifier,
) {

    val lazyListState = rememberLazyListState()
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .semantics { testTag = ViewTags.TAG_USER_LIST_TITLE },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.str_user_list_title),
                        textAlign = TextAlign.Center,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                ),
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)

                .semantics { testTag = ViewTags.TAG_USER_LIST },
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                count = userPagingData.itemCount,
                key = userPagingData.itemKey { it.id },
            ) { index ->
                userPagingData[index]?.let { model ->
                    UserItem(
                        modifier = Modifier.semantics { testTag = ViewTags.TAG_USER_ITEM },
                        model = model,
                        onItemClicked = {
                            onNavigateDetailScreen(model.encodeUserUrl())
                        },
                    )
                }
            }

            when (userPagingData.loadState.append) {
                is LoadState.Error -> item {
                    LoadMoreErrorItem(
                        text = stringResource(R.string.str_user_list_refresh),
                        modifier = Modifier.fillMaxWidth(),
                        onRetry = { userPagingData.retry() },
                    )
                }

                is LoadState.Loading -> item {
                    LoadingItem(modifier = Modifier
                        .fillMaxWidth()
                        .semantics { testTag = ViewTags.TAG_LOADING_ITEM })
                }

                else -> Unit
            }

            if (userPagingData.loadState.source.refresh is LoadState.NotLoading && userPagingData.loadState.append.endOfPaginationReached && userPagingData.itemCount < 1) {
                item {
                    Text(
                        modifier = Modifier
                            .semantics { testTag = ViewTags.TAG_EMPTY_TEXT }
                            .fillMaxWidth()
                            .padding(top = 200.dp),
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.str_user_list_empty),
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun UserListScreenPreview() {
    GitAdminTheme {
        val model = UserModel(
            id = 1,
            username = "roland",
            avatarUrl = "https://avatars.githubusercontent.com/u/28?v=4",
            htmlUrl = "https://github.com/roland",
        )
        val mockData = flowOf(
            PagingData.from(
                data = listOf(model),
                sourceLoadStates = LoadStates(
                    refresh = LoadState.NotLoading(false),
                    append = LoadState.NotLoading(false),
                    prepend = LoadState.NotLoading(false),
                ),
            ),
        ).collectAsLazyPagingItems()
        UserListUIScreen(
            userPagingData = mockData,
            onNavigateDetailScreen = { },
        )
    }
}
