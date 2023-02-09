package com.lenbeta.lenbetaapp.feature.home.student.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.lenbeta.lenbetaapp.feature.home.student.StudentHomeRoute


const val studentHomeRoute = "student_home_route"

fun NavController.navigateToStudentHome(navOptions: NavOptions? = null) {
    this.navigate(studentHomeRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.studentHomeScreen() {
    composable(route = studentHomeRoute) {
        StudentHomeRoute()
    }
}