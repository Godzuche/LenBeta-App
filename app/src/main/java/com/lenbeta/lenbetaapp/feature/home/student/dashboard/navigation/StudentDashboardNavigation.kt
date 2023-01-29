package com.lenbeta.lenbetaapp.feature.home.student.dashboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lenbeta.lenbetaapp.feature.home.student.dashboard.StudentDashboardRoute


const val studentDashboardRoute = "student_dashboard_route"

fun NavGraphBuilder.studentDashboardScreen(){
    composable(route = studentDashboardRoute) {
        StudentDashboardRoute()
    }
}