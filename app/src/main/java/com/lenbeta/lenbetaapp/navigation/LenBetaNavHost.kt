package com.lenbeta.lenbetaapp.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lenbeta.lenbetaapp.feature.onboarding.OnBoardingViewModel
import com.lenbeta.lenbetaapp.feature.onboarding.navigation.onboardingGraph
import com.lenbeta.lenbetaapp.feature.user.navigation.UserDestination
import com.lenbeta.lenbetaapp.feature.user.navigation.userGraph
import com.lenbeta.lenbetaapp.feature.user.student.home.navigation.StudentHomeDestination
import com.lenbeta.lenbetaapp.feature.user.student.home.navigation.studentHomeGraph
import com.lenbeta.lenbetaapp.feature.user.student.student_auth.navigation.StudentAuthDestination
import com.lenbeta.lenbetaapp.feature.user.student.student_auth.navigation.studentAuthGraph
import com.lenbeta.lenbetaapp.feature.user.teacher.TeacherSignInScreen
import com.lenbeta.lenbetaapp.feature.user.teacher.TeacherSignUpScreen
import com.lenbeta.lenbetaapp.feature.util.LenBetaScreen


//@Composable
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LenBetaNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = UserDestination.route
) {
    val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        onboardingGraph(
            onOnboardingFinish = {
                onBoardingViewModel.saveOnboardingState(completed = true)
                navController.navigate(UserDestination.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                }
            }
        )
        userGraph(
            navigateToTeacher = { },
            navigateToStudent = { navController.navigate(StudentAuthDestination.route) },
            nestedGraphs = {
                studentAuthGraph(
                    navigateToHome = {
                        navController
                            .navigate(StudentHomeDestination.route)
                    },
                    navigateToSignIn = {
                        navController
                            .navigate(route = "${StudentAuthDestination.route}/{${StudentAuthDestination.signIn}}")
                    }
                )
//                teacherAuthGraph()
            }
        )
        studentHomeGraph(navController)
        /*composable(route = LenBetaScreen.Welcome.route) {
            WelcomeScreen(navController = navController) *//*{
            onBoardingViewModel.saveOnboardingState(completed = true)
            navController.navigate(
                LenBetaScreen.UserSelection.route,
                NavOptions.Builder()
                    .setPopUpTo(LenBetaScreen.UserSelection.route, inclusive = true).build()
            )
        }*//*
        }*/
        /* composable(route = LenBetaScreen.UserSelection.route) {
             UsersRoute(
                 onTeacherSelected = { navController.navigate(LenBetaScreen.TeacherSignUp.route) },
                 onStudentSelected = { navController.navigate(LenBetaScreen.StudentSignUp.route) }
             )
         }
         composable(route = LenBetaScreen.StudentSignUp.route) {
             StudentSignUpRoute(navController = navController)
         }
         composable(route = LenBetaScreen.StudentSignIn.route) {
             StudentSignInRoute()
         }
         addTeacherAuthGraph(navController)
         addStudentHomeGraph(navController)*/
    }
}

fun NavGraphBuilder.addTeacherAuthGraph(navController: NavHostController) {
    navigation(startDestination = LenBetaScreen.TeacherSignUp.route, route = "teacher_auth") {
        composable(route = LenBetaScreen.TeacherSignUp.route) {
            TeacherSignUpScreen(navController = navController)
        }
        composable(route = LenBetaScreen.TeacherSignIn.route) {
            TeacherSignInScreen()
        }
    }
}