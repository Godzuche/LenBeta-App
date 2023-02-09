package com.lenbeta.lenbetaapp.feature.util

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.withStyle
import com.lenbeta.lenbetaapp.core.ui.icon.LenBetaIcon


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTextField(
    value: String,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions? = null,
    keyboardActions: KeyboardActions? = null,
    placeholder: (@Composable () -> Unit)? = null,
    trailingIcon: LenBetaIcon? = null,
    onTrailingIconClick: () -> Unit = {},
    onValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val defaultKeyboardActions = KeyboardActions(
        onNext = { focusManager.moveFocus(FocusDirection.Down) }
    )
    val defaultKeyboardOptions = KeyboardOptions.Default.copy(
        autoCorrect = false,
        capitalization = KeyboardCapitalization.Sentences,
        imeAction = ImeAction.Next
    )
    TextField(
        value = value,
        label = { Text(stringResource(label)) },
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = keyboardOptions ?: defaultKeyboardOptions,
        keyboardActions = keyboardActions ?: defaultKeyboardActions,
        placeholder = placeholder,
        trailingIcon = {
            trailingIcon?.let {
                when (it) {
                    is LenBetaIcon.ImageVectorIcon -> {
                        Icon(
                            imageVector = it.imageVector,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                onTrailingIconClick.invoke()
                            }
                        )
                    }
                    is LenBetaIcon.PainterResourceIcon -> {
                        Icon(
                            painter = painterResource(it.id),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                onTrailingIconClick.invoke()
                            }
                        )
                    }
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    value: String,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions? = null,
    keyboardActions: KeyboardActions? = null,
    onValueChange: (String) -> Unit,
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val defaultKeyboardActions = KeyboardActions(
        onDone = { focusManager.clearFocus() },
        onNext = { focusManager.moveFocus(FocusDirection.Down) }
    )

    val defaultKeyboardOptions = KeyboardOptions.Default.copy(
        autoCorrect = false,
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Next
    )

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
        keyboardOptions = keyboardOptions ?: defaultKeyboardOptions,
        keyboardActions = keyboardActions ?: defaultKeyboardActions,
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
        Icons.Filled.Visibility
    } else {
        Icons.Filled.VisibilityOff
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