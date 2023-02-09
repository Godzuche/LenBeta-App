package com.lenbeta.lenbetaapp.feature.home.student

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.core.ui.components.SearchField

@Composable
fun StudentHomeRoute() {
    StudentHomeScreen()
}

@Composable
private fun StudentHomeScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        SearchField(
            hint = R.string.db_search_hint,
            onSearch = { queryText ->
                // search while user input
            },
            search = { queryText ->
                // search when search the keyboard onSearch action is called
            },
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(vertical = 24.dp)
        ) {
            item {
                StudentHomeSection(
                    title = R.string.live_class,
                    viewAllButtonText = R.string.total_no_of_classes
                ) {
                    LiveClassCard()
                }
            }
            item {
                StudentHomeSection(
                    title = R.string.assignments,
                    viewAllButtonText = R.string.view_all
                ) {
                    AssignmentsRow()
                }
            }
            items(items = (1..20).toList()) {
                Text(text = it.toString(), fontSize = 24.sp)
            }
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.LibraryBooks,
                    contentDescription = null,
                    modifier = Modifier.size(56.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = "Introduction to Chemical Engineering and Management of Resources",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("8:00 - 10:00 • Practical", style = MaterialTheme.typography.labelSmall)
                }
            }
            /*    data class Item(
                    val fundImage: Int,
                    val title: String,
                    val adImage: Int
                )
                val addFundListItems = listOf(Item(1, "", 1))
                Column {
                    addFundListItems.forEach{
                        val fundImage = it.fundImage
                        val title = it.title
                        val adImage = it.adImage
                        Card {
                            // Todo: Create a card here and pass the values to it
                        }
                    }
                }*/

            Row {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {}) {
                        Text(text = "Join Now")
                    }
                }
                // TODO: Add class image here
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
                Text(
                    text = "Physics",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Icon(imageVector = Icons.Outlined.AccessTime, contentDescription = null)
                Text(
                    text = "🕐 2 days left",
                    style = MaterialTheme.typography.labelMedium,
//                    modifier = Modifier.padding(horizontal = 4.dp)
                )
//                }
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
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.constrainAs(topic) {
                        top.linkTo(column.bottom, margin = 32.dp)
                    }
                )
            }
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = "Study about the laws and the following problems",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.constrainAs(detail) {
                        top.linkTo(topic.bottom, margin = 4.dp)
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
            .then(modifier)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.titleMedium
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = stringResource(viewAllButtonText),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable { }
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun StudentHomePreview() {
    StudentHomeRoute()
}
