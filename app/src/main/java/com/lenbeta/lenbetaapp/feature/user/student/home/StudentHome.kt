package com.lenbeta.lenbetaapp.feature.user.student.home

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.core.navigation.LenBetaNavigationDestination
import com.lenbeta.lenbetaapp.feature.user.student.home.dashboard.StudentDashboardRoute
import com.lenbeta.lenbetaapp.feature.user.student.home.dashboard.StudentDashboardTopBar
import com.lenbeta.lenbetaapp.feature.user.student.home.profile.StudentProfileRoute
import com.lenbeta.lenbetaapp.feature.util.CenteredSmallTopBar
import com.lenbeta.lenbetaapp.feature.util.LenBetaScreen
import com.lenbeta.lenbetaapp.navigation.STUDENT_HOME_TOP_LEVEL_DESTINATIONS
import com.lenbeta.lenbetaapp.navigation.StudentHomeTopLevelDestination

object StudentHomeDestination : LenBetaNavigationDestination {
    override val route: String = "student_home_route"
    override val destination: String = "student_home_destination"
    const val dashboard = "dashboard"
    const val profile = "profile"
}

fun NavGraphBuilder.studentHomeGraph(navController: NavHostController) {
    navigation(
        route = StudentHomeDestination.route,
        startDestination = "${StudentHomeDestination.route}/{${StudentHomeDestination.dashboard}}"
    ) {
        composable(route = "${StudentHomeDestination.route}/{${StudentHomeDestination.dashboard}}") {
            StudentDashboardRoute(navController = navController)
        }
        composable(route = "${StudentHomeDestination.route}/{${StudentHomeDestination.profile}}") {
            StudentProfileRoute(navController = navController)
        }
    }
}

@Composable
fun SetUpTopBar(currentDestination: NavDestination?, navHostController: NavHostController) {
    when {
        currentDestination?.hierarchy?.any {
            it.route == LenBetaScreen.StudentHomeSections.StudentDashboard.route
        } == true -> {
            StudentDashboardTopBar()
        }
        currentDestination?.hierarchy?.any {
            it.route == LenBetaScreen.StudentHomeSections.StudentProfile.route
        } == true -> {
            CenteredSmallTopBar(title = R.string.my_profile, navController = navHostController)
        }
    }
}

@Composable
fun StudentBottomBar(
    onNavigateToStudentHomeTopDestination: (StudentHomeTopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    NavigationBar(

    ) {
        STUDENT_HOME_TOP_LEVEL_DESTINATIONS.forEach { destination ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == destination.route } == true
            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToStudentHomeTopDestination(destination) },
                icon = {
                    Icon(
                        imageVector = if (selected) {
                            destination.selectedIcon
                        } else {
                            destination.unselectedIcon
                        },
                        contentDescription = null
                    )
                },
                label = { Text(text = stringResource(destination.iconTextId)) }
            )
        }
    }
/*    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.secondaryContainer
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(text = "Home")
            },
            alwaysShowLabel = false
        )
        BottomNavigationItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Filled.Explore,
                    contentDescription = null
                )
            },
            label = {
                Text(text = "Explore")
            },
            alwaysShowLabel = false
        )
        BottomNavigationItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Filled.People,
                    contentDescription = null
                )
            },
            label = {
                Text(text = "Peers")
            },
            alwaysShowLabel = false
        )
        BottomNavigationItem(
            selected = false,
            onClick = {
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null
                )
            },
            label = {
                Text(text = "Profile")
            },
            alwaysShowLabel = false
        )
    }*/
}

@Composable
fun StudentBottomNavigation(modifier: Modifier = Modifier) {
    BottomNavigation(
        modifier = modifier
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = {},
            icon = {
                Icon(imageVector = Icons.Filled.Home, contentDescription = null)
            },
            label = {
                Text("Home")
            }
        )
        BottomNavigationItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(imageVector = Icons.Filled.Person, contentDescription = null)
            },
            label = {
                Text("Profile")
            }
        )
    }
}

/*
@Preview
@Composable
fun StudentBottomNavPreview() {
    LenBetaAppTheme {
        StudentBottomBar(
            onNavigateToStudentHomeTopDestination = lenBetaTopLevelNavigation::navigateTo,
            currentDestination = currentDestination
        )
    }
}*/
