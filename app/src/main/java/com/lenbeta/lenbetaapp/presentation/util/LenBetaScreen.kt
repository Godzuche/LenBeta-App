package com.lenbeta.lenbetaapp.presentation.util

sealed class LenBetaScreen(val route: String) {
    object Welcome : LenBetaScreen(route = "welcome_screen")
    object UserSelection : LenBetaScreen(route = "user_selection_screen")
    object StudentSignIn : LenBetaScreen(route = "student_sign_in_screen")
    object StudentSignUp : LenBetaScreen(route = "student_sign_up_screen")
    object TeacherSignUp : LenBetaScreen(route = "teacher_sign_up_screen")
    object TeacherSignIn : LenBetaScreen(route = "teacher_sign_in_screen")
    object StudentMainScreen : LenBetaScreen(route = "student_main_screen")
}
