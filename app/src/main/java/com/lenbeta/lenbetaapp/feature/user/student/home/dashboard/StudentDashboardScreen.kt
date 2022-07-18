package com.lenbeta.lenbetaapp.feature.user.student.home.dashboard

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.core.ui.theme.LenBetaAppTheme
import com.lenbeta.lenbetaapp.feature.util.LenBetaScreen

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
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .then(modifier)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(
            hint = R.string.db_search_hint,
            onSearch = { queryText ->
                // search while user input
            },
            search = { queryText ->
                // search when search the keyboard onSearch action is called
            },
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        StudentHomeSection(title = R.string.live_classes) {
            LiveClassesRow()
        }
    }
}

@Composable
fun LiveClassesRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(liveClassesData) { liveClass ->
            liveClass.run {
                LiveClassElement(
                    drawable = drawable,
                    title = title,
                    started = started,
                    ends = ends,
                    timeLeft = timeLeft
                )
            }
        }
    }
}

@Composable
fun LiveClassElement(
    drawable: ImageVector,
    title: String,
    started: Int,
    ends: Int,
    timeLeft: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .then(modifier)
    ) {
        Icon(imageVector = drawable, contentDescription = null)
        Text(text = title)
        Text(text = "Started: $started")
        Text(text = "Ends: $ends")
        Text(text = "Time Left: $timeLeft")
    }
}

val liveClassesData = List(size = 6) { i ->
    LiveClass(
        drawable = Icons.Filled.LibraryBooks,
        title = "Chemistry",
        started = i,
        ends = i + 1,
        timeLeft = (i + 1) - i
    )
}

data class LiveClass(
    val drawable: ImageVector,
    val title: String,
    val started: Int,
    val ends: Int,
    val timeLeft: Int
)

@Composable
fun StudentHomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.titleMedium
            )
            TextButton(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Text(text = "Show all")
            }
        }
        content()
    }
}

@Composable
fun SearchBar(
    hint: Int,
    onSearch: (String) -> Unit,
    search: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var queryText by remember { mutableStateOf("") }
    val isTextFieldEmpty = queryText.isEmpty()
    OutlinedTextField(
        value = queryText,
        onValueChange = { inputText ->
            queryText = inputText
            // this is for searching while the inputText changes i.e while the user is typing
            onSearch.invoke(inputText)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                modifier = Modifier
                    .clickable(
                        onClickLabel = stringResource(R.string.search),
                        role = Role.Button
                    ) {
                        // search when the search icon is clicked
                        search.invoke(queryText)
                    }
            )
        },
        trailingIcon = {
            // visible if there is input text
            AnimatedVisibility(
                visible = !isTextFieldEmpty,
                enter = expandIn(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    ),
                    expandFrom = Alignment.Center
                ),
                exit = shrinkOut(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMedium
                    ),
                    shrinkTowards = Alignment.Center
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            queryText = ""
                        }
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(text = stringResource(hint))
        },
        label = {
            Text(text = "Search...")
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Words,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { search.invoke(queryText) }
        ),
        singleLine = true,
        shape = MaterialTheme.shapes.extraSmall,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .then(modifier)
    )
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
                        text = "Hello there 👋,",
                        fontSize = 14.sp
                    )
                }
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                    Text(
                        text = username,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
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
        StudentDashboardRoute(rememberNavController())
    }
}