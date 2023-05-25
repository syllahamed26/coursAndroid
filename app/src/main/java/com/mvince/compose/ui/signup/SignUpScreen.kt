package com.mvince.compose.ui.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mvince.compose.R
import com.mvince.compose.ui.Route
import com.mvince.compose.ui.theme.Green700
import com.mvince.compose.ui.theme.GreyLink

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {

    val viewModel = hiltViewModel<SignUpViewModel>()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var displayName by remember { mutableStateOf("") }
    var firstname by remember { mutableStateOf("") }

    val authResource = viewModel.isAuthenticate.collectAsState().value

    LaunchedEffect(key1 = authResource, block = {
        if (authResource.isSingUp) {
            println("add")
            navController.navigate(Route.SIGN_IN)
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
        ) {

            Text(
                text = stringResource(id = R.string.signup),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        errorBorderColor = Color.Red
                    ),
                    leadingIcon = { Icon(
                        painter = painterResource(id = R.drawable.ic_name),
                        contentDescription = null,
                        tint = Color.Black
                    ) },
                    value = displayName,
                    onValueChange = {
                        displayName = it
                    },
                    isError = !authResource.isCorrect,
                    label = {
                        Text(text = stringResource(id = R.string.name))
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        errorBorderColor = Color.Red
                    ),
                    leadingIcon = { Icon(
                        painter = painterResource(id = R.drawable.ic_firstname),
                        contentDescription = null,
                        tint = Color.Black
                    ) },
                    value = firstname,
                    onValueChange = {
                        firstname = it
                    },
                    isError = !authResource.isCorrect,
                    label = {
                        Text(text = stringResource(id = R.string.firstname))
                    },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        errorBorderColor = Color.Red
                    ),
                    leadingIcon = { Icon(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = null,
                        tint = Color.Black
                    ) },
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
                    )
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        errorBorderColor = Color.Red
                    ),
                    leadingIcon = { Icon(
                        painter = painterResource(id = R.drawable.ic_password),
                        contentDescription = null,
                        tint = Color.Black
                    ) },
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
                    visualTransformation = PasswordVisualTransformation()
                )

            }



            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    viewModel.signup(displayName, firstname, email, password)
                },
            ) {
                Text(
                    text = stringResource(id = R.string.register),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            ) {
                Text(text = "Joined us before ?", color = GreyLink, fontSize = 15.sp)
                TextButton(
                    onClick = {
                        navController.navigate("signin")
                    }
                ) {
                    Text("Login", color = Green700, fontSize = 15.sp)
                }
            }
        }
    }
}