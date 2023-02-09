package com.lenbeta.lenbetaapp.feature.authentication.student_auth.sign_in

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.feature.util.AuthButton
import com.lenbeta.lenbetaapp.feature.util.AuthTextButton
import com.lenbeta.lenbetaapp.feature.util.DetailTextField
import com.lenbeta.lenbetaapp.feature.util.PasswordTextField

@Composable
fun StudentSignInRoute(
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    StudentSignInScreen(
        navigateToHome = navigateToHome,
        navigateToSignUp = navigateToSignUp,
        modifier = modifier
    )
}

@Composable
fun StudentSignInScreen(
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    var matNo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        DetailTextField(
            value = matNo,
            label = R.string.mat_no,
            onValueChange = { matNo = it }
        )
        PasswordTextField(
            value = password,
            label = R.string.password,
            keyboardOptions = KeyboardOptions.Default.copy(
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            onValueChange = { password = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        AuthTextButton(
            text = R.string.new_user,
            action = R.string.sign_up,
            icon = Icons.Filled.Login,
            onClick = navigateToSignUp
        )
        AuthButton(
            text = R.string.sign_in,
            onClick = navigateToHome
        )
    }
}

@Preview
@Composable
fun StudentSignInScreenPreview() {
    Surface(color = MaterialTheme.colorScheme.background) {
        StudentSignInRoute(
            navigateToHome = {},
            navigateToSignUp = {}
        )
    }
}