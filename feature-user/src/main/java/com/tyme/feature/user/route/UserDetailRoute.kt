package com.tyme.feature.user.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tyme.component.common.NavRoute
import com.tyme.component.common.navType
import com.tyme.core.model.UserModel
import com.tyme.feature.user.screen.UserDetailScreen
import com.tyme.feature.user.viewmodel.UserDetailViewModel
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf


@Serializable
data class UserDetailRoute(
    val model: UserModel,
) : NavRoute()


fun NavGraphBuilder.userDetailScreen(onBackPress: () -> Unit) {
    composable<UserDetailRoute>(
        typeMap = mapOf(
            typeOf<UserModel>() to navType<UserModel>(),
        ),
    ) {
        UserDetailRoute(
            onBackPress = onBackPress,
        )
    }
}


@Composable
internal fun UserDetailRoute(
    onBackPress: () -> Unit,
    viewModel: UserDetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.refreshData()
    }

    UserDetailScreen(
        uiState = uiState,
        onBackPress = onBackPress,
        modifier = modifier,
    )
}

