package com.lenbeta.lenbetaapp.feature.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.lenbeta.lenbetaapp.feature.chats.chatsScreen
import com.lenbeta.lenbetaapp.feature.connect.navigation.connectScreen
import com.lenbeta.lenbetaapp.feature.explore.navigation.exploreScreen
import com.lenbeta.lenbetaapp.feature.home.student.navigation.studentHomeRoute
import com.lenbeta.lenbetaapp.feature.home.student.navigation.studentHomeScreen
import com.lenbeta.lenbetaapp.feature.notifications.notificationsScreen
import com.lenbeta.lenbetaapp.feature.profile.navigation.studentProfileScreen


const val studentBottomNavRoute = "student_bottom_nav_route"

fun NavGraphBuilder.studentBottomNavGraph() {
    navigation(
        route = studentBottomNavRoute,
        startDestination = studentHomeRoute
    ) {
        studentHomeScreen()
        exploreScreen()
        connectScreen()
        notificationsScreen()
        chatsScreen()
        // TODO: Embed profile screen in home screen as a nested graph
        studentProfileScreen()
    }
}