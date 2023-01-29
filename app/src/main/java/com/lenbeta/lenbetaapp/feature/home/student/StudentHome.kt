package com.lenbeta.lenbetaapp.feature.home

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.feature.util.CenteredSmallTopBar
import com.lenbeta.lenbetaapp.feature.util.LenBetaScreen
import com.lenbeta.lenbetaapp.navigation.STUDENT_HOME_TOP_LEVEL_DESTINATIONS
import com.lenbeta.lenbetaapp.navigation.StudentHomeTopLevelDestination

@Composable
fun SetUpTopBar(currentDestination: NavDestination?, onNavigateUp: () -> Unit) {
    when {
        currentDestination?.hierarchy?.any {
            it.route == LenBetaScreen.StudentHomeTopLevels.StudentDashboard.route
        } == true -> {
//            StudentDashboardTopBar(scrollBehavior = scrollBehavior)
        }
        currentDestination?.hierarchy?.any {
            it.route == LenBetaScreen.StudentHomeTopLevels.StudentProfile.route
        } == true -> {
            CenteredSmallTopBar(title = R.string.my_profile, onNavigateUp = onNavigateUp)
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