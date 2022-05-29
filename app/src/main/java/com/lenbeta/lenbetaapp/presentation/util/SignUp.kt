package com.lenbeta.lenbetaapp.presentation.util

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavHostController
import com.lenbeta.lenbetaapp.R


@Composable
fun SmallAppBar(
    @StringRes title: Int,
    navController: NavHostController
) {
    SmallTopAppBar(
        title = {
            Text(text = stringResource(title))
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.navigateUp() }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back icon"
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}

@Composable
fun DetailTextField(
    value: String,
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    TextField(
        value = value,
        label = { Text(stringResource(label)) },
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    )
}

@Composable
fun PasswordTextField(
    value: String,
    @StringRes label: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = value,
        label = { Text(stringResource(label)) },
        onValueChange = onValueChange,
        singleLine = true,
        trailingIcon = {
            PasswordTrailingIcon(
                isPasswordVisible = isPasswordVisible,
                onIconClick = { isPasswordVisible = !isPasswordVisible }
            )
        },
        visualTransformation = passwordVisualTransformation(
            isPasswordVisible = isPasswordVisible
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    )
}

fun passwordVisualTransformation(isPasswordVisible: Boolean = false): VisualTransformation {
    return if (isPasswordVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }
}

@Composable
fun PasswordTrailingIcon(
    isPasswordVisible: Boolean = false,
    onIconClick: () -> Unit
) {
    val icon = if (isPasswordVisible) {
        Icons.Filled.VisibilityOff
    } else {
        Icons.Filled.Visibility
    }
    val description = if (isPasswordVisible) {
        "Hide password"
    } else {
        "Show password"
    }
    IconButton(onClick = onIconClick) {
        Icon(imageVector = icon, contentDescription = description)
    }
}

@Composable
fun ExistingAccountSignInButton(
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.End)
            .then(modifier)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(text = stringResource(R.string.already_have_an_account))
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = null
            )
        }
    }
}

@Composable
fun SignUpButton(
    @StringRes text: Int,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FilledTonalButton(
        onClick = onSignUpClick,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Text(text = stringResource(text))
    }
}