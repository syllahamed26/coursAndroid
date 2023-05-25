package com.mvince.compose.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mvince.compose.R
import com.mvince.compose.ui.Route
import com.mvince.compose.ui.home.BottomRoute

@Composable
fun NavigationBar(navController: NavController) {
    val bottomNavItems = listOf(
        NavigationBarContent(
            name = "Home",
            route = BottomRoute.HOME,
            icon = painterResource(id = R.drawable.ic_home),
        ),
        NavigationBarContent(
            name = "Game",
            route = BottomRoute.GAME,
            icon = painterResource(id = R.drawable.ic_game),
        ),
        NavigationBarContent(
            name = "Leaderboard",
            route = BottomRoute.LEADERBOARD,
            icon = painterResource(id = R.drawable.ic_leaderboard),
        ),
        NavigationBarContent(
            name = "Profile",
            route = BottomRoute.PROFILE,
            icon = painterResource(id = R.drawable.ic_account),
        ),
    )

    NavigationBar() {
        var selectedItem by remember { mutableStateOf(0) }

        bottomNavItems.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.name) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route)
                },
            )
        }
    }
}

data class NavigationBarContent(
    val name: String,
    val route: String,
    val icon: Painter,
)