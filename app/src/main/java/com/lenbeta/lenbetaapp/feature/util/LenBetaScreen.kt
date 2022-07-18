package com.lenbeta.lenbetaapp.feature.util

import androidx.annotation.StringRes
import com.lenbeta.lenbetaapp.R

sealed class LenBetaScreen(open val route: String, @StringRes open val resourceId: Int? = null) {
    object Welcome : LenBetaScreen(route = "welcome_screen")
    object UserSelection : LenBetaScreen(route = "user_selection_screen")
    object StudentSignIn : LenBetaScreen(route = "student_sign_in_screen")
    object StudentSignUp : LenBetaScreen(route = "student_sign_up_screen")
    object TeacherSignUp : LenBetaScreen(route = "teacher_sign_up_screen")
    object TeacherSignIn : LenBetaScreen(route = "teacher_sign_in_screen")
    sealed class StudentHomeTopLevels(
        override val route: String,
        @StringRes override val resourceId: Int? = null
    ) : LenBetaScreen(route, resourceId) {
        object StudentDashboard : StudentHomeTopLevels(route = "student_dashboard", R.string.home)
        object StudentProfile : StudentHomeTopLevels(route = "student_profile", R.string.profile)
    }
}