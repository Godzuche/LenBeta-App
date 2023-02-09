package com.lenbeta.lenbetaapp.feature.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.navigation
import com.lenbeta.lenbetaapp.feature.chats.chatsScreen
import com.lenbeta.lenbetaapp.feature.connect.navigation.connectScreen
import com.lenbeta.lenbetaapp.feature.edit_profile.navigation.studentEditProfileScreen
import com.lenbeta.lenbetaapp.feature.explore.navigation.exploreScreen
import com.lenbeta.lenbetaapp.feature.home.student.navigation.studentHomeRoute
import com.lenbeta.lenbetaapp.feature.home.student.navigation.studentHomeScreen
import com.lenbeta.lenbetaapp.feature.notifications.notificationsScreen
import com.lenbeta.lenbetaapp.feature.profile.navigation.studentProfileScreen


const val studentMainNavGraphRoute = "student_main_nav_graph_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.studentMainNavGraph() {
    navigation(
        route = studentMainNavGraphRoute,
        startDestination = studentHomeRoute
    ) {
        studentHomeScreen()
        exploreScreen()
        connectScreen()
        notificationsScreen()
        chatsScreen()
        // TODO: Embed profile and edit screen in home screen as a nested graph
        studentProfileScreen()
        studentEditProfileScreen()
    }
}