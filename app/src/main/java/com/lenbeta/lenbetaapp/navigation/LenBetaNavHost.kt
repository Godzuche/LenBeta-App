package com.lenbeta.lenbetaapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.lenbeta.lenbetaapp.feature.authentication.navigation.userAuthGraph
import com.lenbeta.lenbetaapp.feature.authentication.navigation.usersAuthRoute
import com.lenbeta.lenbetaapp.feature.authentication.student_auth.navigation.studentAuthGraph
import com.lenbeta.lenbetaapp.feature.authentication.student_auth.navigation.studentAuthGraphRoute
import com.lenbeta.lenbetaapp.feature.authentication.student_auth.sign_in.navigation.studentSignInRoute
import com.lenbeta.lenbetaapp.feature.authentication.student_auth.sign_up.navigation.studentSignUpRoute
import com.lenbeta.lenbetaapp.feature.authentication.teacher_auth.TeacherSignInScreen
import com.lenbeta.lenbetaapp.feature.authentication.teacher_auth.TeacherSignUpScreen
import com.lenbeta.lenbetaapp.feature.navigation.studentBottomNavGraph
import com.lenbeta.lenbetaapp.feature.navigation.studentBottomNavRoute
import com.lenbeta.lenbetaapp.feature.onboarding.OnBoardingViewModel
import com.lenbeta.lenbetaapp.feature.onboarding.navigation.onboardingScreen


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
                        navController.navigate(
                            studentBottomNavRoute,
                            navOptions {
                                popUpTo(usersAuthRoute)
                            })
                    },
                    navigateToStudentSignIn = {
                        navController.navigate(
                            route = studentSignInRoute,
                            navOptions {
                                popUpTo(usersAuthRoute)
                            }
                        )
                    },
                    navigateToStudentSignUp = {
                        navController.navigate(route = studentSignUpRoute, navOptions {
                            popUpTo(studentSignInRoute)
                        })
                    }
                )
//                teacherAuthGraph()
            }
        )
        studentBottomNavGraph()
    }
}

const val teacherAuthGraphRoute = "teacher_auth_graph"
const val teacherSignInRoute = "teacher_sign_in_route"
const val teacherSignUpRoute = "teacher_sign_up_route"

fun NavGraphBuilder.teacherAuthGraph(navController: NavHostController) {
    navigation(startDestination = teacherSignInRoute, route = teacherAuthGraphRoute) {
        composable(teacherSignUpRoute) {
            TeacherSignUpScreen(navController = navController)
        }
        composable(teacherSignInRoute) {
            TeacherSignInScreen()
        }
    }
}