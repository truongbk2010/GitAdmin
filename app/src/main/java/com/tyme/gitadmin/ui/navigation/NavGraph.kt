package com.tyme.gitadmin.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tyme.feature.home.route.UserListRoute
import com.tyme.feature.home.route.userListScreen
import com.tyme.feature.user.route.UserDetailRoute
import com.tyme.feature.user.route.userDetailScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier,
) {
    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = UserListRoute,
            modifier = modifier.padding(innerPadding),
        ) {

            userListScreen(onNavigateDetailScreen = {
                navController.navigate(UserDetailRoute(it))
            })

            userDetailScreen(onBackPress = {
                navController.popBackStack()
            })

        }
    }
}

