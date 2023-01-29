package com.lenbeta.lenbetaapp.feature.auth.student_auth.sign_in.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lenbeta.lenbetaapp.feature.auth.student_auth.sign_in.StudentSignInRoute

const val studentSignInRoute = "student_sign_in_route"

fun NavGraphBuilder.studentSignInScreen(
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    composable(route = studentSignInRoute) {
        StudentSignInRoute(
            navigateToHome = navigateToHome,
            navigateToSignUp = navigateToSignUp
        )
    }
}