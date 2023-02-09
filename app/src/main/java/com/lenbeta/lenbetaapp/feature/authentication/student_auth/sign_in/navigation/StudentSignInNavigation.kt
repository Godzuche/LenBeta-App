package com.lenbeta.lenbetaapp.feature.authentication.student_auth.sign_in.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.lenbeta.lenbetaapp.feature.authentication.student_auth.sign_in.StudentSignInRoute

const val studentSignInRoute = "student_sign_in_route"

@OptIn(ExperimentalAnimationApi::class)
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