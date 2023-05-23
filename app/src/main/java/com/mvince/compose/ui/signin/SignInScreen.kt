package com.mvince.compose.ui.signin

import android.annotation.SuppressLint
import android.graphics.drawable.shapes.Shape
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColor
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mvince.compose.R
import com.mvince.compose.ui.Route
import com.mvince.compose.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController) {

    val viewModel = hiltViewModel<SignInViewModel>()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var displayPassword by remember { mutableStateOf(false) }

    val authResource = viewModel.signInFlow.collectAsState().value

    var colorChoice = Green700

    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = authResource, block = {
        if (authResource.isSingIn) {
            navController.navigate(Route.HOME)
        }
    })

    Scaffold(
        modifier = Modifier
            .padding(40.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            //horizontalAlignment = Alignment.Center,
        ) {

            Image(modifier = Modifier.fillMaxWidth(), painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "")

            Text(
                text = stringResource(id = R.string.login),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        errorBorderColor = Color.Red
                    ),
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    isError = !authResource.isCorrect,
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

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        errorBorderColor = Color.Red),
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    isError = !authResource.isCorrect,
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

                TextButton(modifier = Modifier.align(alignment = Alignment.End),
                    onClick = {
                              //TODO Faire le mot de passe oublié
                        //navController.navigate("A definir")
                    },
                ) {
                    Text("Forgot Password ?", textAlign = TextAlign.Center)
                }

            }

            Button(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    focusManager.clearFocus()
                    viewModel.signIn(email, password)
                },
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(20.dp))


            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                Text(text = "New to Trivial Pursuit ?", color = GreyLink, fontSize = 15.sp)
                TextButton(
                    onClick = {
                        navController.navigate("signup")
                    }
                ) {
                    Text("Register", color = Green700, fontSize = 15.sp)
                }
            }
        }
    }
}