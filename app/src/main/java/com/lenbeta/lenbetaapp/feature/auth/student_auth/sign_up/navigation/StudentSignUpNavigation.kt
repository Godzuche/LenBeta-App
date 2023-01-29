package com.lenbeta.lenbetaapp.feature.auth.student_auth.sign_up.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lenbeta.lenbetaapp.feature.auth.student_auth.sign_up.StudentSignUpRoute

const val studentSignUpRoute = "student_sign_up_route"

@OptIn(ExperimentalMaterial3Api::class)
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