package com.lenbeta.lenbetaapp.feature.connect.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.lenbeta.lenbetaapp.feature.connect.ConnectRoute

const val connectRoute = "connect_route"

fun NavController.navigateToConnect(navOptions: NavOptions? = null) {
    this.navigate(connectRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.connectScreen() {
    composable(route = connectRoute) {
        ConnectRoute()
    }
}