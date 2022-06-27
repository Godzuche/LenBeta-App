package com.lenbeta.lenbetaapp.feature.user.student.home.peers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PeersRoute(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    PeersScreen(modifier = modifier)
}

@Composable
fun PeersScreen(modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Your peers will appear here", fontSize = 26.sp)
    }
}
