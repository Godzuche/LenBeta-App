package com.lenbeta.lenbetaapp.feature.notifications

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable

const val notificationsRoute = "notifications_route"

fun NavController.navigateToNotifications(navOptions: NavOptions? = null) {
    this.navigate(notificationsRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.notificationsScreen() {
    composable(notificationsRoute) {
        NotificationsRoute()
    }
}