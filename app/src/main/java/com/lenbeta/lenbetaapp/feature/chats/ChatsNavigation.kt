package com.lenbeta.lenbetaapp.feature.chats

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable

const val chatsRoute = "chats_route"

fun NavController.navigateToChats(navOptions: NavOptions? = null) {
    this.navigate(chatsRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.chatsScreen() {
    composable(chatsRoute) {
        ChatsRoute()
    }
}