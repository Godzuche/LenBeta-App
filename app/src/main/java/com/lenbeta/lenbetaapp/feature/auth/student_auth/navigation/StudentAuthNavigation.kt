package com.lenbeta.lenbetaapp.feature.auth.student_auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.lenbeta.lenbetaapp.feature.auth.student_auth.sign_in.navigation.studentSignInRoute
import com.lenbeta.lenbetaapp.feature.auth.student_auth.sign_in.navigation.studentSignInScreen
import com.lenbeta.lenbetaapp.feature.auth.student_auth.sign_up.navigation.studentSignUpScreen

const val studentAuthGraphRoute = "student_auth_graph"

fun NavGraphBuilder.studentAuthGraph(
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    navigation(
        route = studentAuthGraphRoute,
        startDestination = studentSignInRoute
    ) {
        studentSignUpScreen(navigateToHome, navigateToSignIn)
        studentSignInScreen(navigateToHome, navigateToSignUp)
    }
}