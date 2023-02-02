package com.lenbeta.lenbetaapp.navigation

import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.core.ui.icon.LenBetaIcon
import com.lenbeta.lenbetaapp.core.ui.icon.LenBetaIcons

/**
 * Models the navigation top level destinations in the app,
 */
/*class StudentHomeTopLevelNavigation(private val navController: NavHostController) {
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
}*/

enum class StudentTopLevelDestination(
    val selectedIcon: LenBetaIcon,
    val unselectedIcon: LenBetaIcon,
    val iconTextId: Int,
    val titleTextId: Int
) {
    STUDENT_HOME(
        selectedIcon = LenBetaIcon.ImageVectorIcon(LenBetaIcons.Home),
        unselectedIcon = LenBetaIcon.ImageVectorIcon(LenBetaIcons.HomeOutlined),
        iconTextId = R.string.home,
        titleTextId = R.string.app_name
    ),
    EXPLORE(
        selectedIcon = LenBetaIcon.ImageVectorIcon(LenBetaIcons.Explore),
        unselectedIcon = LenBetaIcon.ImageVectorIcon(LenBetaIcons.ExploreOutlined),
        iconTextId = R.string.explore,
        titleTextId = R.string.explore
    ),
    CONNECT(
        selectedIcon = LenBetaIcon.ImageVectorIcon(LenBetaIcons.People),
        unselectedIcon = LenBetaIcon.ImageVectorIcon(LenBetaIcons.PeopleOutlined),
        iconTextId = R.string.connect,
        titleTextId = R.string.connect
    ),
    NOTIFICATIONS(
        selectedIcon = LenBetaIcon.ImageVectorIcon(LenBetaIcons.Notifications),
        unselectedIcon = LenBetaIcon.ImageVectorIcon(LenBetaIcons.NotificationsOutlined),
        iconTextId = R.string.notifications,
        titleTextId = R.string.notifications
    ),
    CHATS(
        selectedIcon = LenBetaIcon.ImageVectorIcon(LenBetaIcons.Chat),
        unselectedIcon = LenBetaIcon.ImageVectorIcon(LenBetaIcons.ChatOutlined),
        iconTextId = R.string.chats,
        titleTextId = R.string.chats
    ),
    USER(
        selectedIcon = LenBetaIcon.ImageVectorIcon(LenBetaIcons.Person),
        unselectedIcon = LenBetaIcon.ImageVectorIcon(LenBetaIcons.PersonOutlined),
        iconTextId = R.string.users,
        titleTextId = R.string.users
    )
}