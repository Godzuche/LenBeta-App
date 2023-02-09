package com.lenbeta.lenbetaapp.feature.edit_profile.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.lenbeta.lenbetaapp.feature.edit_profile.StudentEditProfileRoute

const val studentEditProfileRoute = "student_edit_profile"

fun NavController.navigateToStudentEditProfile(navOptions: NavOptions? = null) {
    this.navigate(studentEditProfileRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.studentEditProfileScreen() {
    composable(studentEditProfileRoute) {
        StudentEditProfileRoute()
    }
}