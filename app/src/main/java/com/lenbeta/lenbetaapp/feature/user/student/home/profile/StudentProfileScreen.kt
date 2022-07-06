package com.lenbeta.lenbetaapp.feature.user.student.home.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.core.ui.theme.LenBetaAppTheme
import com.lenbeta.lenbetaapp.feature.util.CenteredSmallTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentProfileRoute(navController: NavHostController) {
    LenBetaAppTheme {
        Scaffold(
            topBar = {
                CenteredSmallTopBar(title = R.string.my_profile, navController = navController)
            }
        ) { innerPadding ->
            StudentProfileScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSectionCard(title: Int, value: String, modifier: Modifier = Modifier) {
    Card(
        onClick = {},
        modifier = Modifier
            .then(modifier)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                modifier = Modifier
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = stringResource(title),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp
                    )
                }
            }
            OutlinedIconButton(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterEnd),
                border = null
            ) {
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun StudentProfileScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
            .verticalScroll(state = rememberScrollState())

    ) {
        Spacer(modifier = Modifier.height(16.dp))
        ProfileHeader()
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            ProfileSectionCard(
                title = R.string.name,
                value = "God'swill Jonathan"
            )
            ProfileSectionCard(
                title = R.string.student_email,
                value = "Godswill.jonathan@ust.edu.ng"
            )
            ProfileSectionCard(
                title = R.string.engineering,
                value = "Chemical/Petrochemical Engineering"
            )
            ProfileSectionCard(
                title = R.string.level,
                value = "400L"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileHeader(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "Profile picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(156.dp)
                    .clip(CircleShape)
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .align(Alignment.BottomEnd)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(1.dp)

            ) {
                FilledIconButton(
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.BottomEnd),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit button"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
            Text(
                text = "God'swill Jonathan",
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = "godswill.jonathan@ust.edu.ng",
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileSectionCardPreview() {
    LenBetaAppTheme {
        ProfileSectionCard(title = R.string.name, value = "John Doe")
    }
}

@Preview
@Composable
fun StudentProfileBodyPreview() {
    LenBetaAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            StudentProfileScreen()
        }
    }
}

@Preview
@Composable
fun StudentProfileScreenPreview() {
    LenBetaAppTheme { StudentProfileRoute(rememberNavController()) }
}