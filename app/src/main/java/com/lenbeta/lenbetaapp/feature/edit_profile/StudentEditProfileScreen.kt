package com.lenbeta.lenbetaapp.feature.edit_profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.core.ui.icon.LenBetaIcons
import com.lenbeta.lenbetaapp.feature.util.DetailTextField

@Composable
fun StudentEditProfileRoute() {
    StudentEditProfileScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentEditProfileScreen(modifier: Modifier = Modifier) {
    val skillsChipItems = remember {
        mutableStateListOf<String>()
    }
    val listState = rememberLazyListState()

    var skillsInput by remember {
        mutableStateOf("")
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        state = listState,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        item(0) { EditProfileHeader() }
        item(1) {
            val keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Done
            )
            val focusManager = LocalFocusManager.current
            val keyboardActions = KeyboardActions(
                onDone = {
                    when {
                        skillsChipItems.size < 4 -> {
                            skillsChipItems.add(skillsInput)
                            skillsInput = ""
                        }
                        skillsChipItems.size == 4 -> {
                            skillsChipItems.add(skillsInput)
                            skillsInput = ""
                            focusManager.moveFocus(FocusDirection.Next)
                        }
                        else -> {
                            //show toast or snackbar
                        }
                    }
                }
            )
            DetailTextField(
                value = skillsInput,
                label = R.string.skills,
                onValueChange = { skillsInput = it },
                keyboardActions = keyboardActions,
                keyboardOptions = keyboardOptions,
                placeholder = {
                    Text("Enter up to 5 relevant skills...")
                }
            )
        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(skillsChipItems) {
                    InputChip(
                        selected = false,
                        onClick = {},
                        label = {
                            Text(text = it)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EditTextField(
    label: String,
    modifier: Modifier = Modifier
) {
    //
}

@Preview(showBackground = true)
@Composable
fun EditProfileHeader(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        EditProfileImage(modifier)
        Spacer(modifier = Modifier.height(16.dp))
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
            Text(
                text = "God'swill Jonathan",
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = "godswill.jonathan@ust.edu.ng",
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun EditProfileImage(modifier: Modifier) {
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
        ProfileImageEditButton(onClick = { galleryLauncher.launch("image/*") })
    }
}

@Composable
private fun BoxScope.ProfileImageEditButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .align(Alignment.BottomEnd)
            .background(MaterialTheme.colorScheme.background)
            .padding(1.dp)

    ) {
        FilledIconButton(
            onClick = onClick,
            modifier = Modifier
                .align(Alignment.BottomEnd),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        ) {
            Icon(
                imageVector = LenBetaIcons.Edit,
                contentDescription = "Edit button"
            )
        }
    }
}