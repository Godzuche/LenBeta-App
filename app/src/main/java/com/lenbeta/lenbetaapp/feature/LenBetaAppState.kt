package com.lenbeta.lenbetaapp.feature

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lenbeta.lenbetaapp.feature.user.student.home.dashboard.navigation.StudentDashboardDestination
import com.lenbeta.lenbetaapp.feature.user.student.home.explore.navigation.ExploreDestination
import com.lenbeta.lenbetaapp.feature.user.student.home.peers.navigation.PeersDestination
import com.lenbeta.lenbetaapp.feature.user.student.home.profile.navigation.StudentProfileDestination
import com.lenbeta.lenbetaapp.feature.util.LenBetaScreen
import kotlinx.coroutines.CoroutineScope


/**
 * Remembers and creates an instance of [LenBetaAppState]
 */
@Composable
fun rememberLenBetaAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
//    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(scaffoldState, navController, /*snackbarManager,*/ resources, coroutineScope) {
        LenBetaAppState(
            scaffoldState,
            navController,
            /*snackbarManager, */
            resources,
            coroutineScope
        )
    }

/**
 * Responsible for holding state related to [JetsnackApp] and containing UI-related logic.
 */
@Stable
class LenBetaAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
/*    private val snackbarManager: SnackbarManager,*/
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {
    // Process snackbars coming from SnackbarManager
/*    init {
        coroutineScope.launch {
            snackbarManager.messages.collect { currentMessages ->
                if (currentMessages.isNotEmpty()) {
                    val message = currentMessages[0]
                    val text = resources.getText(message.messageId)

                    // Display the snackbar on the screen. `showSnackbar` is a function
                    // that suspends until the snackbar disappears from the screen
                    scaffoldState.snackbarHostState.showSnackbar(text.toString())
                    // Once the snackbar is gone or dismissed, notify the SnackbarManager
                    snackbarManager.setMessageShown(message.id)
                }
            }
        }
    }*/

    // ----------------------------------------------------------
    // BottomBar state source of truth
    // ----------------------------------------------------------

    /*private val bottomBarTabs = listOf(
        LenBetaScreen.StudentHomeSections.StudentDashboard,
        LenBetaScreen.StudentHomeSections.StudentProfile
    )
    private val bottomBarRoutes = bottomBarTabs.map { it.route }*/
    private val bottomBarRoutes = listOf(
        StudentDashboardDestination.route,
        ExploreDestination.route,
        PeersDestination.route,
        StudentProfileDestination.route
    )

    // Reading this attribute will cause recompositions when the bottom bar needs shown, or not.
    // Not all routes need to show the bottom bar.
    val shouldShowBottomBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

    // ----------------------------------------------------------
    // TopBar state source of truth
    // ----------------------------------------------------------

    val topBarEnabledScreens = listOf(
        LenBetaScreen.StudentSignIn,
        LenBetaScreen.StudentSignUp,
        LenBetaScreen.TeacherSignUp,
        LenBetaScreen.TeacherSignIn,
        LenBetaScreen.StudentHomeSections.StudentDashboard,
        LenBetaScreen.StudentHomeSections.StudentProfile
    )

    val topBarEnabledRoutes = topBarEnabledScreens.map { it.route }

    val shouldShowTopBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in topBarEnabledRoutes

    // ----------------------------------------------------------
    // Navigation state source of truth
    // ----------------------------------------------------------

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                // Pop up backstack to the first destination and save state. This makes going back
                // to the start destination when pressing back in any other bottom tab.
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
            }
        }
    }

/*    fun navigateToSnackDetail(snackId: Long, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainDestinations.SNACK_DETAIL_ROUTE}/$snackId")
        }
    }*/
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)


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