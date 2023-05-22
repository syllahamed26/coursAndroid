package com.mvince.compose.ui.signin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mvince.compose.R
import com.mvince.compose.ui.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController) {
    val viewModel = hiltViewModel<SignInViewModel>()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var displayPassword by remember { mutableStateOf(false) }

    val authResource = viewModel.signInFlow.collectAsState().value

    LaunchedEffect(key1 = authResource, block = {
        if(authResource){
            navController.navigate(Route.HOME)
        }
    })

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.signin),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = stringResource(id = R.string.email))
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = stringResource(id = R.string.password))
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (displayPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                AnimatedVisibility(
                    visible = password.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(onClick = { displayPassword = !displayPassword }) {
                        Image(
                            painter = painterResource(
                            id = if (displayPassword) R.drawable.ic_visibility_off else R.drawable.ic_visibility_on
                        ),
                        contentDescription = "VisiblePassword"
                    )
                    }
                }
            },
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                println("email: $email")
                println("password: $password")
                viewModel.signIn(email, password)
            },
        ) {
            Text(
                text = stringResource(id = R.string.signin),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(id = R.string.signup),
            modifier = Modifier.clickable {
                navController.navigate("signup")
            },
            style = MaterialTheme.typography.titleMedium
        )
    }
}