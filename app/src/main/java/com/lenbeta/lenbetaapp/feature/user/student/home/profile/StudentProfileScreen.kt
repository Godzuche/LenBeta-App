package com.lenbeta.lenbetaapp.feature.user.student.home.profile

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.core.ui.theme.LenBetaAppTheme
import com.lenbeta.lenbetaapp.feature.util.CenteredSmallTopBar
import kotlinx.coroutines.launch

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
            .height(72.dp)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSectionExpandableCard(
    title: Int,
    items: List<String>,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    var expanded by remember { mutableStateOf(false) }
    Card(
        onClick = {
            expanded = !expanded
            if (expanded) {
                scope.launch {
                    listState.animateScrollToItem(
                        listState.layoutInfo.visibleItemsInfo.lastIndex
                    )
                }
            }
        },
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
                    .padding(horizontal = 16.dp)
                    .animateContentSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = stringResource(title),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                if (items.isNotEmpty()) {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                        Text(
                            text = items.first(),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    }
                    AnimatedVisibility(
                        visible = expanded,
                        enter = expandVertically(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessMedium
                            )
                        ),
                        exit = shrinkVertically(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessMedium
                            )
                        )
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier
                                .wrapContentHeight()
                        ) {
                            for (index in 1 until items.size) {
                                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                                    Text(
                                        text = items.elementAt(index),
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
            OutlinedIconButton(
                onClick = { expanded = !expanded },
                modifier = Modifier.align(Alignment.TopEnd),
                border = null
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun StudentProfileScreen(modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        state = listState,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        item(0) { ProfileHeader() }
        item(1) { Spacer(modifier = Modifier.height(24.dp)) }
        item(2) {
            ProfileSectionCard(
                title = R.string.name,
                value = "God'swill Jonathan"
            )
        }
        item(3) {
            ProfileSectionCard(
                title = R.string.student_email,
                value = "Godswill.jonathan@ust.edu.ng"
            )
        }
        item(4) {
            ProfileSectionCard(
                title = R.string.engineering,
                value = "Chemical/Petrochemical Engineering"
            )
        }
        item(5) {
            ProfileSectionCard(
                title = R.string.level,
                value = "400L"
            )
        }
        /*    item(6) {
                ProfileSectionCard(
                    title = R.string.skills,
                    value = "Android Development"
                )
            }*/
        item(6) {
            ProfileSectionExpandableCard(
                title = R.string.skills,
                items = listOf(
                    "Android Development",
                    "Technical Writing",
                    "Public Speaking",
                    "Git/GitHub",
                    "Machine Learning"
                ),
                listState = listState
            )
        }
        /*item(7) {
            Spacer(modifier = Modifier.height(16.dp))
        }*/
    }
}

@Composable
fun ProfileHeader(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        ProfileImage(modifier)
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

@Composable
private fun ProfileImage(modifier: Modifier) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
/*    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }*/
    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
/*        *//*     imageUri?.let {
                 if (Build.VERSION.SDK_INT < 28) {
                     bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                 } else {
                     val source = ImageDecoder.createSource(context.contentResolver, it)
                     bitmap.value = ImageDecoder.decodeBitmap(source)
                 }
                 *//**//*bitmap.value?.let { btm ->
                Image(
                    bitmap = btm.asImageBitmap(),
                    contentDescription = "Profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(156.dp)
                        .clip(CircleShape)
                )
            }*//**//*
        }*/
        val imageRequest = ImageRequest.Builder(context)
            .data(imageUri ?: R.drawable.avatar)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
        /*val painter = rememberAsyncImagePainter(
            model = model,
            contentScale = ContentScale.Crop
        )*/
        AsyncImage(
            model = imageRequest,
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Profile picture",
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.High,
            modifier = Modifier
                .size(156.dp)
                .clip(CircleShape)
        )
        ProfileImageEditButton(galleryLauncher)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun BoxScope.ProfileImageEditButton(galleryLauncher: ManagedActivityResultLauncher<String, Uri?>) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .align(Alignment.BottomEnd)
            .background(MaterialTheme.colorScheme.background)
            .padding(1.dp)

    ) {
        FilledIconButton(
            onClick = { galleryLauncher.launch("image/*") },
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