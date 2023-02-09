package com.lenbeta.lenbetaapp.feature.authentication.student_auth.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.navigation
import com.lenbeta.lenbetaapp.feature.authentication.student_auth.sign_in.navigation.studentSignInRoute
import com.lenbeta.lenbetaapp.feature.authentication.student_auth.sign_in.navigation.studentSignInScreen
import com.lenbeta.lenbetaapp.feature.authentication.student_auth.sign_up.navigation.studentSignUpScreen

const val studentAuthGraphRoute = "student_auth_graph"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.studentAuthGraph(
    navigateToHome: () -> Unit,
    navigateToStudentSignIn: () -> Unit,
    navigateToStudentSignUp: () -> Unit
) {
    navigation(
        route = studentAuthGraphRoute,
        startDestination = studentSignInRoute
    ) {
        studentSignUpScreen(navigateToHome, navigateToStudentSignIn)
        studentSignInScreen(navigateToHome, navigateToStudentSignUp)
    }
}