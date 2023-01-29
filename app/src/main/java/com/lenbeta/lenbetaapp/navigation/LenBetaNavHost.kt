package com.lenbeta.lenbetaapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lenbeta.lenbetaapp.feature.auth.navigation.userAuthGraph
import com.lenbeta.lenbetaapp.feature.auth.navigation.usersAuthRoute
import com.lenbeta.lenbetaapp.feature.auth.student_auth.navigation.studentAuthGraph
import com.lenbeta.lenbetaapp.feature.auth.student_auth.navigation.studentAuthGraphRoute
import com.lenbeta.lenbetaapp.feature.auth.student_auth.sign_in.navigation.studentSignInRoute
import com.lenbeta.lenbetaapp.feature.auth.student_auth.sign_up.navigation.studentSignUpRoute
import com.lenbeta.lenbetaapp.feature.auth.teacher_auth.TeacherSignInScreen
import com.lenbeta.lenbetaapp.feature.auth.teacher_auth.TeacherSignUpScreen
import com.lenbeta.lenbetaapp.feature.home.student.navigation.studentHomeGraph
import com.lenbeta.lenbetaapp.feature.home.student.navigation.studentHomeRoute
import com.lenbeta.lenbetaapp.feature.onboarding.OnBoardingViewModel
import com.lenbeta.lenbetaapp.feature.onboarding.navigation.onboardingScreen
import com.lenbeta.lenbetaapp.feature.util.LenBetaScreen


//@Composable
@Composable
fun LenBetaNavHost(
    navController: NavHostController,
//    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = usersAuthRoute
) {
    val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        onboardingScreen(
            onOnboardingFinish = {
                onBoardingViewModel.saveOnboardingState(completed = true)
                navController.navigate(usersAuthRoute) {
                    popUpTo(navController.graph.findStartDestination().id)
                }
            }
        )
        userAuthGraph(
            navigateToTeacher = { },
            navigateToStudent = { navController.navigate(studentAuthGraphRoute) },
            nestedGraphs = {
                studentAuthGraph(
                    navigateToHome = {
                        navController.navigate(studentHomeRoute)
                    },
                    navigateToSignIn = {
                        navController.navigate(route = studentSignInRoute)
                    },
                    navigateToSignUp = {
                        navController.navigate(route = studentSignUpRoute)
                    }
                )
//                teacherAuthGraph()
            }
        )
        studentHomeGraph(
            onNavigateUp = { navController.navigateUp() }
        )
    }
}

fun NavGraphBuilder.teacherAuthGraph(navController: NavHostController) {
    navigation(startDestination = LenBetaScreen.TeacherSignUp.route, route = "teacher_auth") {
        composable(route = LenBetaScreen.TeacherSignUp.route) {
            TeacherSignUpScreen(navController = navController)
        }
        composable(route = LenBetaScreen.TeacherSignIn.route) {
            TeacherSignInScreen()
        }
    }
}