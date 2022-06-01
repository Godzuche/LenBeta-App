package com.lenbeta.lenbetaapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lenbeta.lenbetaapp.presentation.UserSelection
import com.lenbeta.lenbetaapp.presentation.onboarding.OnBoardingViewModel
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
    val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = LenBetaScreen.Welcome.route) {
            WelcomeScreen() {
                onBoardingViewModel.saveOnboardingState(completed = true)
                navController.navigate(
                    LenBetaScreen.UserSelection.route,
                    NavOptions.Builder()
                        .setPopUpTo(LenBetaScreen.UserSelection.route, inclusive = true).build()
                )
            }
        }
        composable(route = LenBetaScreen.UserSelection.route) {
            UserSelection(
                onTeacherSelected = { navController.navigate(LenBetaScreen.TeacherSignUp.route) },
                onStudentSelected = { navController.navigate(LenBetaScreen.StudentSignUp.route) }
            )
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