package com.lenbeta.lenbetaapp.feature.user.student.home

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.feature.user.student.home.dashboard.StudentDashboardTopBar
import com.lenbeta.lenbetaapp.feature.util.CenteredSmallTopBar
import com.lenbeta.lenbetaapp.feature.util.LenBetaScreen
import com.lenbeta.lenbetaapp.navigation.STUDENT_HOME_TOP_LEVEL_DESTINATIONS
import com.lenbeta.lenbetaapp.navigation.StudentHomeTopLevelDestination

@Composable
fun SetUpTopBar(currentDestination: NavDestination?, navHostController: NavHostController) {
    when {
        currentDestination?.hierarchy?.any {
            it.route == LenBetaScreen.StudentHomeTopLevels.StudentDashboard.route
        } == true -> {
            StudentDashboardTopBar()
        }
        currentDestination?.hierarchy?.any {
            it.route == LenBetaScreen.StudentHomeTopLevels.StudentProfile.route
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
    NavigationBar() {
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
                label = { Text(text = stringResource(destination.iconTextId)) },
                alwaysShowLabel = false
            )
        }
    }
}