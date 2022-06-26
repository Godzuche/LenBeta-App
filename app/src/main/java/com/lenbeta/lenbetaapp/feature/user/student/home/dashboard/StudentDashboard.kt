package com.lenbeta.lenbetaapp.feature.user.student.home.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lenbeta.lenbetaapp.core.ui.theme.LenBetaAppTheme
import com.lenbeta.lenbetaapp.feature.user.student.home.SetUpTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDashboardRoute(navController: NavHostController) {
    Scaffold(
        topBar = {
//            val navBackStackEntry by navController.currentBackStackEntryAsState()
//            val currentDestination = navBackStackEntry?.destination
////            val currentRoute = currentDestination?.hierarchy?.any { it.route ==  }
//            SetUpTopBar(currentDestination, navController)
////            HomeTopBar()
            StudentDashboardTopBar()
        }
    ) { innerPadding ->

        StudentDashboardScreen(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun StudentDashboardScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
    }
}

@Composable
fun StudentDashboardTopBar(
    modifier: Modifier = Modifier,
    username: String = "God'swill Jonathan",
) {
    SmallTopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {},
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
            }
        },
        title = {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = "Hello there,",
                        fontSize = 16.sp
                    )
                }
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                    Text(
                        text = username,
                        fontSize = 18.sp
                    )
                }
            }
        },
        modifier = modifier,
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Outlined.Notifications, contentDescription = null)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun StudentHomePreview() {
    LenBetaAppTheme {
        StudentDashboardScreen()
    }
}