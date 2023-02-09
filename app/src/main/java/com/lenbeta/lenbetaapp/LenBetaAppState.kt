package com.lenbeta.lenbetaapp

import android.content.res.Resources
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.lenbeta.lenbetaapp.feature.authentication.navigation.usersAuthRoute
import com.lenbeta.lenbetaapp.feature.authentication.student_auth.sign_in.navigation.studentSignInRoute
import com.lenbeta.lenbetaapp.feature.authentication.student_auth.sign_up.navigation.studentSignUpRoute
import com.lenbeta.lenbetaapp.feature.chats.chatsRoute
import com.lenbeta.lenbetaapp.feature.chats.navigateToChats
import com.lenbeta.lenbetaapp.feature.connect.navigation.connectRoute
import com.lenbeta.lenbetaapp.feature.connect.navigation.navigateToConnect
import com.lenbeta.lenbetaapp.feature.explore.navigation.exploreRoute
import com.lenbeta.lenbetaapp.feature.explore.navigation.navigateToExplore
import com.lenbeta.lenbetaapp.feature.home.student.navigation.navigateToStudentHome
import com.lenbeta.lenbetaapp.feature.home.student.navigation.studentHomeRoute
import com.lenbeta.lenbetaapp.feature.notifications.navigateToNotifications
import com.lenbeta.lenbetaapp.feature.notifications.notificationsRoute
import com.lenbeta.lenbetaapp.feature.onboarding.navigation.onboardingRoute
import com.lenbeta.lenbetaapp.feature.profile.navigation.studentProfileRoute
import com.lenbeta.lenbetaapp.navigation.StudentTopLevelDestination
import com.lenbeta.lenbetaapp.navigation.StudentTopLevelDestination.*
import com.lenbeta.lenbetaapp.navigation.teacherSignInRoute
import com.lenbeta.lenbetaapp.navigation.teacherSignUpRoute
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberLenBetaAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberAnimatedNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): LenBetaAppState {
    return remember(navController, coroutineScope) {
        LenBetaAppState(navController, coroutineScope)
    }
}

@Stable
class LenBetaAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: StudentTopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            studentHomeRoute -> STUDENT_HOME
            exploreRoute -> EXPLORE
            connectRoute -> CONNECT
            notificationsRoute -> NOTIFICATIONS
            chatsRoute -> CHATS
            else -> null
        }

    val studentBottomBarTopLevelDestinations: List<StudentTopLevelDestination> =
        StudentTopLevelDestination.values().toList().dropLast(1)

    private val bottomBarRoutes = listOf(
        studentHomeRoute,
        exploreRoute,
        connectRoute,
        notificationsRoute,
        chatsRoute
    )

    // Reading this attribute will cause recompositions when the bottom bar needs shown, or not.
    // Not all routes need to show the bottom bar.
    val shouldShowBottomBar: Boolean
        @Composable get() = currentDestination?.route in bottomBarRoutes

    // ----------------------------------------------------------
    // TopBar state source of truth
    // ----------------------------------------------------------
    // List of screen routes that show show a top app bar
    val topBarEnabledRoutes = listOf(
        studentSignInRoute,
        studentSignUpRoute,
        teacherSignUpRoute,
        teacherSignInRoute,
        studentHomeRoute,
        studentProfileRoute,
        connectRoute,
        exploreRoute
    )

    val topBarDisabledRoutes = listOf(onboardingRoute, usersAuthRoute)

    val shouldShowTopBar: Boolean
        @Composable get() = !topBarDisabledRoutes.contains(currentDestination?.route)

    fun navigateToRoute(route: String) {
        navController.navigate(route)
    }

    fun navigateToTopLevelDestination(studentTopLevelDestination: StudentTopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            /*popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }*/
            popUpTo(studentHomeRoute) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (studentTopLevelDestination) {
            STUDENT_HOME -> navController.navigateToStudentHome(topLevelNavOptions)
            EXPLORE -> navController.navigateToExplore(topLevelNavOptions)
            CONNECT -> navController.navigateToConnect(topLevelNavOptions)
            NOTIFICATIONS -> navController.navigateToNotifications(topLevelNavOptions)
            CHATS -> navController.navigateToChats(topLevelNavOptions)
            else -> Unit
        }
    }

    fun onNavigateUp() {
        navController.navigateUp()
    }

    fun onBackClick() {
        navController.popBackStack()
    }

}

/**
 * A composable function that returns the [Resources]. It will be recomposed when `Configuration`
 * gets updated.
 */
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}