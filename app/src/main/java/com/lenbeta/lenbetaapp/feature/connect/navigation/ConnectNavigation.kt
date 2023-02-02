package com.lenbeta.lenbetaapp.feature.connect.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.lenbeta.lenbetaapp.feature.connect.ConnectRoute

const val connectRoute = "connect_route"

fun NavController.navigateToConnect(navOptions: NavOptions? = null) {
    this.navigate(connectRoute, navOptions)
}

fun NavGraphBuilder.connectScreen() {
    composable(route = connectRoute) {
        ConnectRoute()
    }
}