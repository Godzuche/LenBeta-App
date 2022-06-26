package com.lenbeta.lenbetaapp.feature.user.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lenbeta.lenbetaapp.core.navigation.LenBetaNavigationDestination
import com.lenbeta.lenbetaapp.feature.UsersRoute

object UserDestination : LenBetaNavigationDestination {
    override val route = "user_route"
    override val destination = "user_destination"
}

fun NavGraphBuilder.userGraph(
    navigateToTeacher: () -> Unit,
    navigateToStudent: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = UserDestination.route,
        startDestination = UserDestination.destination
    ) {
        composable(route = UserDestination.destination) {
            UsersRoute(
                onTeacherSelected = navigateToTeacher,
                onStudentSelected = navigateToStudent
            )
        }
        nestedGraphs()
    }
}