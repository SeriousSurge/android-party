package com.hiremarknolan.android_party_compose.presentation.servers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hiremarknolan.android_party_compose.R
import com.hiremarknolan.android_party_compose.presentation.Screen
import com.hiremarknolan.android_party_compose.presentation.servers.components.ServerListItem
import com.hiremarknolan.android_party_compose.presentation.ui.theme.Dimensions

@Composable
fun ServersScreen(
    navController: NavController,
    viewModel: ServersViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            Topbar {
                viewModel.logout()
                navController.navigate(Screen.LoginScreen.route)
            }

            if (state.servers.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.servers) { server ->
                        ServerListItem(
                            server
                        )
                    }
                }
            }

        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun Topbar(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Test.io",
            fontSize = 30.sp,
            modifier = modifier
                .padding(Dimensions.SpacingR1)
                .height(Dimensions.topAppBarHeight),

        )
        Image(
            painter = painterResource(id = R.drawable.ico_logout),
            contentDescription = "logout",
            modifier = modifier
                .padding(horizontal = Dimensions.SpacingR6)
                .clickable { onLogout() }
        )
    }
}
