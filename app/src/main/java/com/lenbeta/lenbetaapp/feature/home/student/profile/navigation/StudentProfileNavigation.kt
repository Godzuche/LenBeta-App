package com.lenbeta.lenbetaapp.feature.home.student.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lenbeta.lenbetaapp.feature.home.student.profile.StudentProfileRoute

const val studentProfileRoute = "student_profile_route"

fun NavGraphBuilder.studentProfileScreen(
    onNavigateUp: () -> Unit
) {
    composable(route = studentProfileRoute) {
        StudentProfileRoute(onNavigateUp = onNavigateUp)
    }
}