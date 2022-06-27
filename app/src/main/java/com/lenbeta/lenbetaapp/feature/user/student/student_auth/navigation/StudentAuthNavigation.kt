package com.lenbeta.lenbetaapp.feature.user.student.student_auth.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lenbeta.lenbetaapp.core.navigation.LenBetaNavigationDestination
import com.lenbeta.lenbetaapp.feature.user.student.student_auth.sign_in.StudentSignInRoute
import com.lenbeta.lenbetaapp.feature.user.student.student_auth.sign_up.StudentSignUpRoute

object StudentAuthDestination : LenBetaNavigationDestination {
    override val route = "student_auth_route"
    override val destination = "student_auth_destination"
    const val signIn = "signIn"
    const val signUp = "signUp"
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.studentAuthGraph(
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit
) {
    navigation(
        route = StudentAuthDestination.route,
        startDestination = "${StudentAuthDestination.route}/{${StudentAuthDestination.signUp}}"
    ) {
        composable(route = "${StudentAuthDestination.route}/{${StudentAuthDestination.signUp}}") {
            StudentSignUpRoute(
                navigateToHome = navigateToHome,
                navigateToSignIn = navigateToSignIn
            )
        }
        composable(route = "${StudentAuthDestination.route}/{${StudentAuthDestination.signIn}}") {
            StudentSignInRoute()
        }
    }
}