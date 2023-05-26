package com.mvince.compose.ui.components.topbar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mvince.compose.R
import com.mvince.compose.ui.Route
import com.mvince.compose.ui.components.IconUser
import com.mvince.compose.ui.theme.PrimaryGradiant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(
    navController: NavController,
    id: String,
    firstName: String,
    lastName: String,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    textStyle: TextStyle = MaterialTheme.typography.headlineSmall,
) {

    val viewModel = hiltViewModel<TopNavigationBarViewModel>()

    var user = viewModel.user.collectAsState().value
    val isLogout = viewModel.isLogout.collectAsState().value

    LaunchedEffect(key1 = isLogout, block = {
        if (isLogout) {
            navController.navigate(Route.SIGN_IN)
        }
    })

    LaunchedEffect(key1 = user, block = {
        if (user != null) {
            user = user
        }
    })


    var expanded by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = PrimaryGradiant),
        title = {
            Text(
                stringResource(id = R.string.app_name),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
        },
        actions = {
            Box(){
                IconButton(onClick = { expanded = true }) {
                    if (user != null){
                        IconUser(
                            id = "id",
                            firstName = "${user?.displayName?.get(0)}",
                            lastName = "${user?.firstname?.get(0)}"
                        )
                    }else{
                        IconUser(
                            id = "id",
                            firstName = "T",
                            lastName = "P"
                        )
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Logout") },
                        onClick = { viewModel.signOut() },
                        leadingIcon = {
                            Icon(
                                painterResource(id = R.drawable.ic_login),
                                contentDescription = null
                            )
                        })
                }
            }
        }
    )
}
