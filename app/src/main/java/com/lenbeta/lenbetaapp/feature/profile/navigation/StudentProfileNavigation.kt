package com.lenbeta.lenbetaapp.feature.profile.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.lenbeta.lenbetaapp.feature.profile.StudentProfileRoute

const val studentProfileRoute = "student_profile_route"

fun NavController.navigateToStudentProfile(navOptions: NavOptions? = null) {
    this.navigate(studentProfileRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.studentProfileScreen() {
    composable(route = studentProfileRoute) {
        StudentProfileRoute()
    }
}