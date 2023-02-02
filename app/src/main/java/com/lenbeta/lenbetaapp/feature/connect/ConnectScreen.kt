package com.lenbeta.lenbetaapp.feature.connect

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun ConnectRoute(
    modifier: Modifier = Modifier
) {
    ConnectScreen(modifier = modifier)
}

@Composable
fun ConnectScreen(modifier: Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Connect with peers and communities", fontSize = 26.sp)
    }
}
