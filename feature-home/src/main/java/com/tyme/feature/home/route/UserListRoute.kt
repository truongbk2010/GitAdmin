package com.tyme.feature.home.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.tyme.component.common.NavRoute
import com.tyme.core.model.UserModel
import com.tyme.feature.home.screen.UserListUIScreen
import com.tyme.feature.home.viewmodel.UserListViewModel
import kotlinx.serialization.Serializable

@Serializable
data object UserListRoute : NavRoute()

fun NavGraphBuilder.userListScreen(onNavigateDetailScreen: ((UserModel) -> Unit)) {
    composable<UserListRoute> {
        UserListRoute(onNavigateDetailScreen)
    }
}


@Composable
internal fun UserListRoute(
    onNavigateDetailScreen: ((UserModel) -> Unit),
    viewModel: UserListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {

    val userPagingData = viewModel.listUserPagingData.collectAsLazyPagingItems()

    UserListUIScreen(
        userPagingData = userPagingData,
        onNavigateDetailScreen = onNavigateDetailScreen,
        modifier = modifier,
    )
}
