package com.lenbeta.lenbetaapp.presentation.student

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lenbeta.lenbetaapp.presentation.theme.LenBetaAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentMainScreen() {
    Scaffold(
        topBar = {
            HomeTopBar()
        },
        bottomBar = {
            StudentBottomNav()
        }
    ) { innerPadding ->
        StudentHomeBody(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun StudentHomeBody(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
    }
}

@Composable
fun HomeTopBar(
    username: String = "God'swill Jonathan",
    modifier: Modifier = Modifier,
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

@Composable
fun StudentBottomNav(modifier: Modifier = Modifier) {
    BottomNavigation(
        modifier = modifier,
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
    }
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

@Preview
@Composable
fun StudentBottomNavPreview() {
    LenBetaAppTheme {
        StudentBottomNav()
    }
}

@Preview(showBackground = true)
@Composable
fun StudentHomePreview() {
    LenBetaAppTheme {
        StudentMainScreen()
    }
}