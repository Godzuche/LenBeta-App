package com.lenbeta.lenbetaapp.feature.home.student.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.lenbeta.lenbetaapp.feature.home.student.dashboard.navigation.studentDashboardRoute
import com.lenbeta.lenbetaapp.feature.home.student.dashboard.navigation.studentDashboardScreen
import com.lenbeta.lenbetaapp.feature.home.student.explore.navigation.exploreScreen
import com.lenbeta.lenbetaapp.feature.home.student.peers.navigation.peersScreen
import com.lenbeta.lenbetaapp.feature.home.student.profile.navigation.studentProfileScreen


const val studentHomeRoute = "student_home_route"

fun NavGraphBuilder.studentHomeGraph(
    onNavigateUp: () -> Unit
) {
    navigation(
        route = studentHomeRoute,
        startDestination = studentDashboardRoute
    ) {
        studentDashboardScreen()
        exploreScreen()
        peersScreen()
        studentProfileScreen(onNavigateUp = onNavigateUp)
    }
}