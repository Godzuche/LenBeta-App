package com.lenbeta.lenbetaapp.feature

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.lenbeta.lenbetaapp.LenBetaAppState
import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.core.ui.components.LenBetaNavigationBar
import com.lenbeta.lenbetaapp.core.ui.components.LenBetaNavigationBarItem
import com.lenbeta.lenbetaapp.core.ui.components.TopBar
import com.lenbeta.lenbetaapp.core.ui.icon.LenBetaIcon
import com.lenbeta.lenbetaapp.feature.authentication.student_auth.sign_in.navigation.studentSignInRoute
import com.lenbeta.lenbetaapp.feature.authentication.student_auth.sign_up.navigation.studentSignUpRoute
import com.lenbeta.lenbetaapp.feature.edit_profile.navigation.studentEditProfileRoute
import com.lenbeta.lenbetaapp.feature.profile.navigation.studentProfileRoute
import com.lenbeta.lenbetaapp.navigation.LenBetaNavHost
import com.lenbeta.lenbetaapp.navigation.StudentTopLevelDestination
import com.lenbeta.lenbetaapp.rememberLenBetaAppState

@ExperimentalMaterial3Api
@Composable
fun LenBetaApp(
    startDestination: String,
    windowSizeClass: WindowSizeClass,
    appState: LenBetaAppState = rememberLenBetaAppState(windowSizeClass = windowSizeClass)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        rememberTopAppBarState()
    )
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                shouldShowTopBar = appState.shouldShowTopBar,
                topLevelDestination = appState.currentTopLevelDestination,
                currentDestination = appState.currentDestination,
                scrollBehavior = scrollBehavior,
                navigateToProfile = { appState.navigateToRoute(studentProfileRoute) },
                navigateToSettings = {},
                navigateUp = { appState.onBackClick() },
                editProfile = { appState.navigateToRoute(studentEditProfileRoute) }
            )
        },
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                LenBetaBottomBar(
                    destinations = appState.studentBottomBarTopLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination
                )
            }
        }
    ) { innerPaddingModifier ->
        LenBetaNavHost(
            navController = appState.navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPaddingModifier)
        )
    }
}

@Composable
fun LenBetaBottomBar(
    destinations: List<StudentTopLevelDestination>,
    onNavigateToDestination: (StudentTopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    LenBetaNavigationBar(modifier = modifier) {
        destinations.forEach { destination ->
            val selected =
                currentDestination.isStudentTopLevelDestinationInHierarchy(destination)
            LenBetaNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val icon =
                        if (selected) destination.selectedIcon else destination.unselectedIcon
                    when (icon) {
                        is LenBetaIcon.ImageVectorIcon -> {
                            Icon(imageVector = icon.imageVector, contentDescription = null)
                        }
                        is LenBetaIcon.PainterResourceIcon -> {
                            Icon(painter = painterResource(icon.id), contentDescription = null)
                        }
                    }
                }
            )
        }
    }
}

private fun NavDestination?.isStudentTopLevelDestinationInHierarchy(destination: StudentTopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

// TODO: Change the return type to Int for the string resource id
fun String.toTitleTextId(): Int {
    return when (this) {
        studentSignInRoute, studentSignUpRoute -> R.string.empty_string
        studentProfileRoute -> R.string.profile
        studentEditProfileRoute -> R.string.edit_profile
        else -> R.string.empty_string
    }
}