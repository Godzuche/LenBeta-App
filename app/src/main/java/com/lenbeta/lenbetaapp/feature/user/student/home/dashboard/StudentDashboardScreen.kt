package com.lenbeta.lenbetaapp.feature.user.student.home.dashboard

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Timelapse
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.core.ui.theme.LenBetaAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDashboardRoute(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarScrollState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
//            val navBackStackEntry by navController.currentBackStackEntryAsState()
//            val currentDestination = navBackStackEntry?.destination
////            val currentRoute = currentDestination?.hierarchy?.any { it.route ==  }
//            SetUpTopBar(currentDestination, navController)
////            HomeTopBar()
            StudentDashboardTopBar(scrollBehavior = scrollBehavior)
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
        StudentHomeSection(
            title = R.string.live_class,
            viewAllButtonText = R.string.total_no_of_classes
        ) {
            LiveClassCard()
        }
        StudentHomeSection(
            title = R.string.assignments,
            viewAllButtonText = R.string.view_all
        ) {
            AssignmentsRow()
        }
        (1..20).forEach { index ->
            Text(text = index.toString(), fontSize = 24.sp)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun LiveClassCard() {
    Card(
        onClick = {}, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Icon(
                    imageVector = Icons.Filled.LibraryBooks,
                    contentDescription = null,
                    modifier = Modifier.size(56.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("History", fontSize = 18.sp)
                    Text("8:00 - 10:00", fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {}) {
                Text(text = "Join Now")
            }
        }
    }
}

@Composable
fun AssignmentsRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(assignmentsData) { assignment ->
            assignment.run {
                AssignmentElement(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignmentElement(
    drawable: ImageVector,
    title: String,
    started: Int,
    ends: Int,
    timeLeft: Int,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = {},
        modifier = Modifier.width((LocalConfiguration.current.screenWidthDp.dp - 32.dp))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(16.dp)
                .then(modifier),
        ) {
            /*     Icon(imageVector = drawable, contentDescription = null)
                 Text(text = title)
                 Text(text = "Started: $started")
                 Text(text = "Ends: $ends")
                 Text(text = "Time Left: $timeLeft")*/
            val (column, box, topic, detail) = createRefs()
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(column) {
                        top.linkTo(parent.top)
                    }
            ) {
                Text(text = "Physics", fontSize = 22.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Outlined.AccessTime, contentDescription = null)
                    Text(
                        text = "2 days left",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 32.dp, vertical = 16.dp)
                    .wrapContentSize()
                    .constrainAs(box) {
                        end.linkTo(parent.end)
                    }

            ) {
                Icon(
                    imageVector = Icons.Filled.LibraryBooks,
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )
            }
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                Text(
                    text = "Laws of motion",
                    fontSize = 22.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.constrainAs(topic) {
                        top.linkTo(column.bottom, margin = 32.dp)
                    }
                )
            }
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "Study about the laws and the following problems",
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.constrainAs(detail) {
                        top.linkTo(topic.bottom)
                    }
                )
            }
        }
    }
}

val assignmentsData = List(size = 6) { i ->
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
    viewAllButtonText: Int,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .then(modifier)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = stringResource(title),
                fontSize = 18.sp
            )
            TextButton(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = stringResource(viewAllButtonText))
                }
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
        /*colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),*/
        placeholder = {
            Text(text = stringResource(hint))
        },
        label = {
            Text(text = "Search")
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
    scrollBehavior: TopAppBarScrollBehavior,
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
        },
        scrollBehavior = scrollBehavior
    )
}

@Preview(showBackground = true)
@Composable
fun StudentHomePreview() {
    LenBetaAppTheme {
        StudentDashboardRoute(rememberNavController())
    }
}

/*
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SwitchButtonPreview() {
    Switch(
        checked = false,
        onCheckedChange = {},
        colors = SwitchDefaults.colors(
            checkedTrackColor = Color.Black,
            checkedThumbColor = Color.White
        )
    )
}*/
