package com.lenbeta.lenbetaapp.feature.authentication.student_auth.sign_up

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.feature.util.*

@ExperimentalMaterial3Api
@Composable
fun StudentSignUpRoute(
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    StudentSignUpScreen(
        navigateToHome = navigateToHome,
        navigateToSignIn = navigateToSignIn,
        modifier = modifier
    )
}

@Composable
fun StudentSignUpScreen(
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var matNo by rememberSaveable { mutableStateOf("") }
    var department by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .then(modifier)
    ) {
        DetailTextField(
            value = firstName,
            label = R.string.first_name,
            onValueChange = { firstName = it }
        )
        DetailTextField(
            value = lastName,
            label = R.string.last_name,
            onValueChange = { lastName = it }
        )
        DetailTextField(
            value = matNo,
            label = R.string.mat_no,
            onValueChange = { matNo = it }
        )
        DetailTextField(
            value = department,
            label = R.string.department,
            onValueChange = { department = it }
        )
        PasswordTextField(
            value = password,
            label = R.string.password,
            onValueChange = { password = it }
        )
        PasswordTextField(
            value = confirmPassword,
            label = R.string.confirm_password,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            onValueChange = { confirmPassword = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        AuthTextButton(
            text = R.string.already_have_an_account,
            action = R.string.sign_in,
            icon = Icons.Filled.Login,
            onClick = navigateToSignIn
        )
        AuthButton(
            text = R.string.sign_up,
            onClick = navigateToHome
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun StudentSignUpScreenPreview() {
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
//            StudentSignUpRoute(navController = rememberNavController())
    }
}