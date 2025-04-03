package com.tyme.feature.home

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tyme.core.model.UserModel
import com.tyme.feature.home.screen.UserListUIScreen
import com.tyme.feature.home.screen.ui.ViewTags
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserListUIScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testUserListScreenHasPagingData() {
        val mockUserList =
            Array(3) {
                UserModel(
                    id = it.toLong(),
                    username = "roland",
                    avatarUrl = "https://avatars.githubusercontent.com/u/28?v=4",
                    htmlUrl = "https://github.com/roland",
                )
            }.toList()

        composeTestRule.setContent {
            val mockData =
                flowOf(
                    PagingData.from(
                        data = mockUserList,
                        sourceLoadStates =
                        LoadStates(
                            refresh = LoadState.NotLoading(true),
                            append = LoadState.NotLoading(true),
                            prepend = LoadState.NotLoading(true),
                        ),
                    ),
                ).collectAsLazyPagingItems()

            UserListUIScreen(
                userPagingData = mockData,
                onNavigateDetailScreen = { },
            )
        }

        // Verify user list screen visible
        composeTestRule.onNodeWithTag(ViewTags.TAG_USER_LIST_TITLE).assertIsDisplayed()

        // Verify that the UserList is displayed
        composeTestRule
            .onNodeWithTag(ViewTags.TAG_USER_LIST)
            .assertIsDisplayed()

        // Verify that the correct number of user items are displayed
        composeTestRule
            .onAllNodesWithTag(ViewTags.TAG_USER_ITEM)
            .assertCountEquals(mockUserList.size)
    }

    @Test
    fun testUserListScreenWithEmptyData() {
        composeTestRule.setContent {
            val mockData =
                flowOf(
                    PagingData.empty<UserModel>(),
                ).collectAsLazyPagingItems()

            UserListUIScreen(
                userPagingData = mockData,
                onNavigateDetailScreen = { },
            )
        }

        // Verify user list screen visible
        composeTestRule.onNodeWithTag(ViewTags.TAG_USER_LIST_TITLE).assertIsDisplayed()

        // Verify that the empty text are displayed
        composeTestRule
            .onNodeWithTag(ViewTags.TAG_EMPTY_TEXT)
            .assertIsNotDisplayed()
    }
}
