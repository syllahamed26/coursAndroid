package com.mvince.compose.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mvince.compose.ui.details.DetailsScreen
import com.mvince.compose.ui.signin.SignInScreen
import com.mvince.compose.ui.signup.SignUpScreen
import com.mvince.compose.ui.users.UsersScreen

@Composable
fun ComposeApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.SIGN_IN
    ) {
        composable(Route.SIGN_IN) { backStackEntry ->
            SignInScreen(navController = navController)
        }
        composable(Route.SIGN_UP) { backStackEntry ->
            SignUpScreen(navController = navController)
        }
        composable(
            route = "${Route.DETAIL}/{${Argument.USERNAME}}",
            arguments = listOf(
                navArgument(Argument.USERNAME) {
                    type = NavType.StringType
                }
            ),
        ) {
            DetailsScreen(navController)
        }
    }
}

object Route {
    const val USER = "user"
    const val DETAIL = "detail"
    const val SIGN_UP = "signup"
    const val SIGN_IN = "signin"
}

object Argument {
    const val USERNAME = "username"
}