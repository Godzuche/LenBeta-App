package com.lenbeta.lenbetaapp.feature.user.student.student_auth.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lenbeta.lenbetaapp.core.navigation.LenBetaNavigationDestination
import com.lenbeta.lenbetaapp.feature.user.student.student_auth.sign_in.StudentSignInRoute
import com.lenbeta.lenbetaapp.feature.user.student.student_auth.sign_in.navigation.StudentSignInNavigation
import com.lenbeta.lenbetaapp.feature.user.student.student_auth.sign_up.StudentSignUpRoute
import com.lenbeta.lenbetaapp.feature.user.student.student_auth.sign_up.navigation.StudentSignUpNavigation

object StudentAuthDestination : LenBetaNavigationDestination {
    override val route = "student_auth_route"
    override val destination = StudentSignInNavigation.route
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.studentAuthGraph(
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    navigation(
        route = StudentAuthDestination.route,
        startDestination = StudentAuthDestination.destination
    ) {
        composable(route = StudentSignUpNavigation.route) {
            StudentSignUpRoute(
                navigateToHome = navigateToHome,
                navigateToSignIn = navigateToSignIn
            )
        }
        composable(route = StudentSignInNavigation.route) {
            StudentSignInRoute(
                navigateToHome = navigateToHome,
                navigateToSignUp = navigateToSignUp
            )
        }
    }
}