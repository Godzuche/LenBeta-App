package com.lenbeta.lenbetaapp.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.core.ui.icon.LenBetaIcon
import com.lenbeta.lenbetaapp.core.ui.icon.LenBetaIcons
import com.lenbeta.lenbetaapp.feature.toTitleTextId
import com.lenbeta.lenbetaapp.navigation.StudentTopLevelDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentHomeTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navigateToProfile: () -> Unit,
    navigateToSettings: () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: LenBetaIcon? = LenBetaIcon.PainterResourceIcon(R.drawable.avatar),
    username: String = "God'swill Jonathan"
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = navigateToProfile) {
                val iconModifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                when (navigationIcon) {
                    is LenBetaIcon.PainterResourceIcon -> {
                        Image(
                            painter = painterResource(navigationIcon.id),
                            contentDescription = stringResource(R.string.profile_pic_icon),
                            modifier = iconModifier
                        )
                    }
                    is LenBetaIcon.ImageVectorIcon -> {
                        Image(
                            imageVector = navigationIcon.imageVector,
                            contentDescription = stringResource(R.string.profile_pic_icon),
                            modifier = iconModifier
                        )
                    }
                    else -> Unit
                }
            }
        },
        title = {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
//                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "Hello there 👋,",
                    style = MaterialTheme.typography.titleSmall
                )
//                }
//                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                Text(
                    text = username,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.clickable { navigateToProfile.invoke() }
                )
//                }
            }
        },
        actions = {
            IconButton(onClick = navigateToSettings) {
                Icon(imageVector = LenBetaIcons.SettingsOutlined, contentDescription = null)
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    shouldShowTopBar: Boolean,
    topLevelDestination: StudentTopLevelDestination?,
    scrollBehavior: TopAppBarScrollBehavior,
    currentDestination: NavDestination?,
    navigateToProfile: () -> Unit,
    navigateToSettings: () -> Unit,
    navigateUp: () -> Unit
) {
    if (shouldShowTopBar) {
        if (topLevelDestination != null) {
            if (topLevelDestination == StudentTopLevelDestination.STUDENT_HOME) {
                StudentHomeTopAppBar(
                    scrollBehavior = scrollBehavior,
                    navigateToProfile = navigateToProfile,
                    navigateToSettings = navigateToSettings
                )
            } else {
                CenterAlignedTopAppBar(
                    title = {
                        Text(text = stringResource(topLevelDestination.titleTextId))
                    }
                )
            }
        } else {
            currentDestination?.route?.let {
                CenterAlignedTopAppBar(
                    title = {
//                    currentDestination?.route?.let { Text(it) }
                        Text(it.toTitleTextId())
                    },
                    navigationIcon = {
//                    if (currentDestination?.route != null) {
                        IconButton(onClick = navigateUp) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
//                    }
                    }
                )
            }
        }
    }
}