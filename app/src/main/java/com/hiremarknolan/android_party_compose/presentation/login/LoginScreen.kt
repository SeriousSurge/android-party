package com.hiremarknolan.android_party_compose.presentation.login

import android.graphics.fonts.FontFamily
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.navigation.NavController
import com.hiremarknolan.android_party_compose.R
import com.hiremarknolan.android_party_compose.domain.model.UserCredentials
import com.hiremarknolan.android_party_compose.presentation.Screen
import com.hiremarknolan.android_party_compose.presentation.ui.theme.Dimensions

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    if (state.token != null) navController.navigate(Screen.ServerScreen.route)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.bg), contentScale = ContentScale.Crop),
        contentAlignment = Alignment.Center

    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val username = remember { mutableStateOf(TextFieldValue()) }
            val password = remember { mutableStateOf(TextFieldValue()) }


            val keyboardController = LocalSoftwareKeyboardController.current

            Text(text = "Testio.", style = TextStyle(fontSize = 40.sp))

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                modifier = Modifier.background(
                    Color.White,
                    RoundedCornerShape(Dimensions.roundedCorners)
                ),
                label = { Text(text = "Username") },
                leadingIcon = {
                    Icon(
                        painterResource(id = R.drawable.ico_username),
                        contentDescription = "Password",
                        tint = Color.Gray
                    )
                },
                value = username.value,
                onValueChange = { username.value = it },
            )

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                modifier = Modifier.background(
                    Color.White,
                    RoundedCornerShape(Dimensions.roundedCorners)
                ),
                label = { Text(text = "Password") },
                leadingIcon = {
                    Icon(
                        painterResource(id = R.drawable.ico_lock),
                        contentDescription = "Password",
                        tint = Color.Gray
                    )
                },
                value = password.value,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { password.value = it })

            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(30.dp, 0.dp, 30.dp, 0.dp)) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.Green,
                            RoundedCornerShape(Dimensions.roundedCorners)
                        )
                        .height(50.dp),
                    onClick = {
                        viewModel.handleLoginEvent(
                            UserCredentials(
                                username.value.text,
                                password.value.text
                            )
                        )
                        keyboardController?.hide()
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Green,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Login")
                }
            }

        }
    }

    if (state.isLoading) CircularProgressIndicator()

}