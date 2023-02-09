package com.lenbeta.lenbetaapp.feature.authentication.student_auth.sign_up.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.lenbeta.lenbetaapp.feature.authentication.student_auth.sign_up.StudentSignUpRoute

const val studentSignUpRoute = "student_sign_up_route"

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
fun NavGraphBuilder.studentSignUpScreen(
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit
) {
    composable(route = studentSignUpRoute) {
        StudentSignUpRoute(
            navigateToHome = navigateToHome,
            navigateToSignIn = navigateToSignIn
        )
    }
}