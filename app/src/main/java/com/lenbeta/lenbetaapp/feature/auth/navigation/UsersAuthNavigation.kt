package com.lenbeta.lenbetaapp.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lenbeta.lenbetaapp.feature.user.UsersRoute

const val usersAuthGraphRoute = "user_graph"
const val usersAuthRoute = "user_route"

fun NavGraphBuilder.userAuthGraph(
    navigateToTeacher: () -> Unit,
    navigateToStudent: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = usersAuthGraphRoute,
        startDestination = usersAuthRoute
    ) {
        composable(route = usersAuthRoute) {
            UsersRoute(
                navigateToTeacher = navigateToTeacher,
                navigateToStudent = navigateToStudent
            )
        }
        nestedGraphs()
    }
}