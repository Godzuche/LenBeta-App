package com.lenbeta.lenbetaapp.feature.util

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle


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
fun AuthTextButton(
    @StringRes text: Int,
    @StringRes action: Int,
    onClick: () -> Unit,
    icon: ImageVector,
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
            Text(text = buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                    append(
                        stringResource(text)
                    )
                }
                append(" ")
                withStyle(
                    SpanStyle(color = MaterialTheme.colorScheme.primary)
                ) {
                    append(stringResource(action))
                }
            }
            )
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        }
    }
}

@Composable
fun AuthButton(
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        Text(text = stringResource(text))
    }
}