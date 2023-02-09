package com.lenbeta.lenbetaapp.feature.authentication.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.lenbeta.lenbetaapp.feature.user.UsersRoute

const val usersAuthGraphRoute = "user_graph"
const val usersAuthRoute = "user_route"

@OptIn(ExperimentalAnimationApi::class)
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
                navigateToTeacherAuth = navigateToTeacher,
                navigateToStudentAuth = navigateToStudent
            )
        }
        nestedGraphs()
    }
}