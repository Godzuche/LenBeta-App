package com.lenbeta.lenbetaapp.core.ui.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

object LenBetaIcons {
    val Home = Icons.Filled.Home
    val HomeOutlined = Icons.Outlined.Home
    val Explore = Icons.Filled.Explore
    val ExploreOutlined = Icons.Outlined.Explore
    val People = Icons.Filled.People
    val PeopleOutlined = Icons.Outlined.People
    val Person = Icons.Filled.Person
    val PersonOutlined = Icons.Outlined.Person
    val Notifications = Icons.Filled.Notifications
    val NotificationsOutlined = Icons.Outlined.Notifications
    val Chat = Icons.Filled.Mail
    val ChatOutlined = Icons.Outlined.MailOutline
    val SettingsOutlined = Icons.Outlined.Settings
}

sealed interface LenBetaIcon {
    data class ImageVectorIcon(val imageVector: ImageVector): LenBetaIcon
    data class PainterResourceIcon(@DrawableRes val id: Int): LenBetaIcon
}