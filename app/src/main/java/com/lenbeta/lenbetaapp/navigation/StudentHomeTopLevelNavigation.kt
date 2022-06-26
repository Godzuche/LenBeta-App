package com.lenbeta.lenbetaapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.lenbeta.lenbetaapp.R.string.home
import com.lenbeta.lenbetaapp.R.string.profile
import com.lenbeta.lenbetaapp.feature.user.student.home.StudentHomeDestination
import com.lenbeta.lenbetaapp.feature.util.LenBetaScreen

/**
 * Models the navigation top level destinations in the app,
 */
class StudentHomeTopLevelNavigation(private val navController: NavHostController) {
    fun navigateTo(destination: StudentHomeTopLevelDestination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}

data class StudentHomeTopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)

val STUDENT_HOME_TOP_LEVEL_DESTINATIONS = listOf(
/*    LenBetaScreen.StudentHomeSections.StudentDashboard,
    LenBetaScreen.StudentHomeSections.StudentProfile,*/
    StudentHomeTopLevelDestination(
        route = "${StudentHomeDestination.route}/{${StudentHomeDestination.dashboard}}",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextId = home
    ),
    StudentHomeTopLevelDestination(
        route = "${StudentHomeDestination.route}/{${StudentHomeDestination.profile}}",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        iconTextId = profile
    )
)