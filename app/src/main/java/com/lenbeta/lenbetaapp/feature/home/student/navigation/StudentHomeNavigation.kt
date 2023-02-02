package com.lenbeta.lenbetaapp.feature.home.student.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.lenbeta.lenbetaapp.feature.home.student.StudentHomeRoute


const val studentHomeRoute = "student_home_route"

fun NavController.navigateToStudentHome(navOptions: NavOptions? = null) {
    this.navigate(studentHomeRoute, navOptions)
}

fun NavGraphBuilder.studentHomeScreen() {
    composable(route = studentHomeRoute) {
        StudentHomeRoute()
    }
}