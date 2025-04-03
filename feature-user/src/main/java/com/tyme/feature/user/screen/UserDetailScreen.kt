package com.tyme.feature.user.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Group
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tyme.component.R
import com.tyme.component.theme.GitAdminTheme
import com.tyme.component.ui.LoadingItem
import com.tyme.component.ui.VerticalTextIcon
import com.tyme.core.model.UserDetailModel
import com.tyme.feature.user.state.UserDetailUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UserDetailScreen(
    uiState: UserDetailUiState,
    onBackPress: (() -> Unit),
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        onBackPress.invoke()
                    }) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = null,
                        )
                    }
                },
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.str_user_detail_title),
                        textAlign = TextAlign.Center,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                ),
            )
        },
    ) { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .background(Color.White)
                .fillMaxSize(),
            contentAlignment = Alignment.TopStart,
        ) {
            val user = uiState.user
            if (user != null) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    UserDetailItem(user)
                    FollowerSection(followers = user.followers, followings = user.following)
                    BlogSection(url = user.htmlUrl, modifier = Modifier.fillMaxWidth())

                }
            }

            if (uiState.isLoading) {
                LoadingItem(modifier = Modifier.align(Alignment.Center))
            }

        }
    }
}

@Composable
private fun FollowerSection(modifier: Modifier = Modifier, followers: Long, followings: Long) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(24.dp)) {
        VerticalTextIcon(icon = {
            Icon(
                Icons.Filled.Group,
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
                    .padding(4.dp),
                contentDescription = null,
            )
        }) {
            Text(
                text = buildString {
                    append(followers)
                    append("\n")
                    append(stringResource(R.string.str_user_detail_follower))
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 1.dp),
            )
        }

        VerticalTextIcon(icon = {
            Icon(
                Icons.Filled.Badge,
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
                    .padding(4.dp),
                contentDescription = null,
            )
        }) {
            Text(
                text = buildString {
                    append(followings)
                    append("\n")
                    append(stringResource(R.string.str_user_detail_following))
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 1.dp),
            )
        }
    }
}

@Composable
private fun BlogSection(modifier: Modifier = Modifier, url: String) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.str_user_detail_blog),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 1.dp),
        )
        Text(
            text = url,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 1.dp),
        )
    }
}


@Preview
@Composable
private fun UserDetailScreenPreview() {
    GitAdminTheme {
        val userModel = UserDetailModel(
            id = 1,
            username = "roland",
            avatarUrl = "https://avatars.githubusercontent.com/u/28?v=4",
            htmlUrl = "https://github.com/roland",
            followers = 100,
            following = 200,
        )

        UserDetailScreen(
            uiState = UserDetailUiState(isLoading = true, user = userModel),
            onBackPress = { },
        )
    }
}