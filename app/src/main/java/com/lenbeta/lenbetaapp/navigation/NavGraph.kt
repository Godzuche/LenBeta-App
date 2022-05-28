package com.lenbeta.lenbetaapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lenbeta.lenbetaapp.presentation.UserSelection
import com.lenbeta.lenbetaapp.presentation.onboarding.WelcomeScreen
import com.lenbeta.lenbetaapp.presentation.student.StudentSignInScreen
import com.lenbeta.lenbetaapp.presentation.student.StudentSignUpScreen
import com.lenbeta.lenbetaapp.presentation.teacher.TeacherSignInScreen
import com.lenbeta.lenbetaapp.presentation.teacher.TeacherSignUpScreen
import com.lenbeta.lenbetaapp.presentation.util.LenBetaScreen

@ExperimentalMaterial3Api
@Composable
fun SetUpNavGraph(
    startDestination: String,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = LenBetaScreen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = LenBetaScreen.UserSelection.route) {
            UserSelection(navController = navController)
        }
        composable(route = LenBetaScreen.StudentSignUp.route) {
            StudentSignUpScreen(navController = navController)
        }
        composable(route = LenBetaScreen.StudentSignIn.route) {
            StudentSignInScreen()
        }
        composable(route = LenBetaScreen.TeacherSignUp.route) {
            TeacherSignUpScreen(navController = navController)
        }
        composable(route = LenBetaScreen.TeacherSignIn.route) {
            TeacherSignInScreen()
        }
    }
}