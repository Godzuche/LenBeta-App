package com.lenbeta.lenbetaapp.feature.notifications

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val notificationsRoute = "notifications_route"

fun NavController.navigateToNotifications(navOptions: NavOptions? = null) {
    this.navigate(notificationsRoute, navOptions)
}

fun NavGraphBuilder.notificationsScreen() {
    composable(notificationsRoute) {
        NotificationsRoute()
    }
}