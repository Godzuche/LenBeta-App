package com.lenbeta.lenbetaapp.feature.user.student.home.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ExploreRoute(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    ExploreScreen(modifier = modifier)
}

@Composable
fun ExploreScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Explore the World", fontSize = 26.sp)
    }
}