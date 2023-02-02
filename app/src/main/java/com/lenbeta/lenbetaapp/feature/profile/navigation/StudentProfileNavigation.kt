package com.lenbeta.lenbetaapp.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.lenbeta.lenbetaapp.feature.profile.StudentProfileRoute

const val studentProfileRoute = "student_profile_route"

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(studentProfileRoute, navOptions)
}

fun NavGraphBuilder.studentProfileScreen() {
    composable(route = studentProfileRoute) {
        StudentProfileRoute()
    }
}