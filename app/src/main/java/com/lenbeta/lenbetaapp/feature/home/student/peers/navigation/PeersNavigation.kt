package com.lenbeta.lenbetaapp.feature.home.student.peers.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lenbeta.lenbetaapp.feature.home.student.peers.PeersRoute

const val peersRoute = "peers_route"

fun NavGraphBuilder.peersScreen() {
    composable(route = peersRoute) {
        PeersRoute()
    }
}