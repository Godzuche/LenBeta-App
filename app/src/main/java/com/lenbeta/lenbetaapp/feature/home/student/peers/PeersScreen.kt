package com.lenbeta.lenbetaapp.feature.home.student.peers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun PeersRoute(
    modifier: Modifier = Modifier
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
