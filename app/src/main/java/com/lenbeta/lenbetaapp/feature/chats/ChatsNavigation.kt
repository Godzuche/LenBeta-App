package com.lenbeta.lenbetaapp.feature.chats

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val chatsRoute = "chats_route"

fun NavController.navigateToChats(navOptions: NavOptions? = null) {
    this.navigate(chatsRoute, navOptions)
}

fun NavGraphBuilder.chatsScreen() {
    composable(chatsRoute) {
        ChatsRoute()
    }
}