package com.lenbeta.lenbetaapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.lenbeta.lenbetaapp.R.string.*
import com.lenbeta.lenbetaapp.feature.home.student.dashboard.navigation.studentDashboardRoute
import com.lenbeta.lenbetaapp.feature.home.student.explore.navigation.exploreRoute
import com.lenbeta.lenbetaapp.feature.home.student.peers.navigation.peersRoute
import com.lenbeta.lenbetaapp.feature.home.student.profile.navigation.studentProfileRoute

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
    StudentHomeTopLevelDestination(
        route = studentDashboardRoute,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextId = home
    ),
    StudentHomeTopLevelDestination(
        route = exploreRoute,
        selectedIcon = Icons.Filled.Explore,
        unselectedIcon = Icons.Outlined.Explore,
        iconTextId = explore
    ),
    StudentHomeTopLevelDestination(
        route = peersRoute,
        selectedIcon = Icons.Filled.People,
        unselectedIcon = Icons.Outlined.People,
        iconTextId = peers
    ),
    StudentHomeTopLevelDestination(
        route = studentProfileRoute,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        iconTextId = my_profile
    )
)