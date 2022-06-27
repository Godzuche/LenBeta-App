package com.lenbeta.lenbetaapp.feature.user.student.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lenbeta.lenbetaapp.core.navigation.LenBetaNavigationDestination
import com.lenbeta.lenbetaapp.feature.user.student.home.dashboard.StudentDashboardRoute
import com.lenbeta.lenbetaapp.feature.user.student.home.dashboard.navigation.StudentDashboardDestination
import com.lenbeta.lenbetaapp.feature.user.student.home.explore.ExploreRoute
import com.lenbeta.lenbetaapp.feature.user.student.home.explore.navigation.ExploreDestination
import com.lenbeta.lenbetaapp.feature.user.student.home.peers.PeersRoute
import com.lenbeta.lenbetaapp.feature.user.student.home.peers.navigation.PeersDestination
import com.lenbeta.lenbetaapp.feature.user.student.home.profile.StudentProfileRoute
import com.lenbeta.lenbetaapp.feature.user.student.home.profile.navigation.StudentProfileDestination

object StudentHomeDestination : LenBetaNavigationDestination {
    override val route: String = "student_home_route"
    override val destination: String = StudentDashboardDestination.route
}

fun NavGraphBuilder.studentHomeGraph(navController: NavHostController) {
    navigation(
        route = StudentHomeDestination.route,
        startDestination = StudentHomeDestination.destination
    ) {
        composable(route = StudentDashboardDestination.route) {
            StudentDashboardRoute(navController = navController)
        }
        composable(route = ExploreDestination.route) {
            ExploreRoute(navController = navController)
        }
        composable(route = PeersDestination.route) {
            PeersRoute(navController = navController)
        }
        composable(route = StudentProfileDestination.route) {
            StudentProfileRoute(navController = navController)
        }
    }
}