package com.lenbeta.lenbetaapp.feature

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lenbeta.lenbetaapp.core.ui.theme.LenBetaAppTheme
import com.lenbeta.lenbetaapp.feature.user.student.home.StudentBottomBar
import com.lenbeta.lenbetaapp.navigation.LenBetaNavHost
import com.lenbeta.lenbetaapp.navigation.StudentHomeTopLevelNavigation

@ExperimentalMaterial3Api
@Composable
fun LenBetaApp(startDestination: String) {
    LenBetaAppTheme {
        val appState = rememberLenBetaAppState()
        val lenBetaTopLevelNavigation = remember(appState.navController) {
            StudentHomeTopLevelNavigation(appState.navController)
        }

        val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        Scaffold(
            topBar = {
                /*if (appState.shouldShowTopBar) {
                    val currentScreen = appState.navController
                        .currentBackStackEntryAsState().value?.destination?.route
                    appState.topBarEnabledScreensf
                    CenteredSmallTopBar(title = currentScreen, navController = )
                }*/
            },
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    StudentBottomBar(
                        onNavigateToStudentHomeTopDestination = lenBetaTopLevelNavigation::navigateTo,
                        currentDestination = currentDestination
                    )
                }
            }
        ) { innerPaddingModifier ->
            LenBetaNavHost(
                modifier = Modifier.padding(innerPaddingModifier),
                navController = appState.navController,
                startDestination = startDestination
            )
        }
    }
}