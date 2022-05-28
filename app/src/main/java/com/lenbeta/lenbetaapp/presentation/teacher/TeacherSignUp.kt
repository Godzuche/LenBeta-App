package com.lenbeta.lenbetaapp.presentation.teacher

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lenbeta.lenbetaapp.R
import com.lenbeta.lenbetaapp.presentation.student.DetailTextField
import com.lenbeta.lenbetaapp.presentation.student.SignUpButton
import com.lenbeta.lenbetaapp.presentation.theme.LenBetaAppTheme
import com.lenbeta.lenbetaapp.presentation.util.LenBetaScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherSignUpScreen(navController: NavHostController) {
    LenBetaAppTheme {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.sign_up))
                    },
                    navigationIcon = {
                        IconButton(onClick = {navController.navigateUp()}) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "back icon",
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            TeacherSignUpScreenContent(modifier = Modifier.padding(innerPadding),
                navController = navController)
        }
    }
}

@Composable
fun TeacherSignUpScreenContent(modifier: Modifier = Modifier, navController: NavHostController) {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var faculty by rememberSaveable { mutableStateOf("") }
    var department by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    val focusManager = LocalFocusManager.current
    val keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next
    )
    val onNextAction = KeyboardActions(
        onNext = { focusManager.moveFocus(FocusDirection.Down) }
    )
    val onDoneAction = KeyboardActions(
        onDone = { focusManager.clearFocus() }
    )

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
            keyboardOptions = keyboardOptions,
            keyboardActions = onNextAction,
            onValueChange = { firstName = it }
        )
        DetailTextField(
            value = lastName,
            label = R.string.last_name,
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = onNextAction,
            onValueChange = { lastName = it }
        )
        DetailTextField(
            value = faculty,
            label = R.string.faculty,
            keyboardOptions = keyboardOptions,
            keyboardActions = onNextAction,
            onValueChange = { faculty = it }
        )
        DetailTextField(
            value = department,
            label = R.string.department,
            keyboardOptions = keyboardOptions,
            keyboardActions = onNextAction,
            onValueChange = { department = it }
        )
        DetailTextField(
            value = password,
            label = R.string.password,
            keyboardOptions = keyboardOptions,
            keyboardActions = onNextAction,
            onValueChange = { password = it }
        )
        DetailTextField(
            value = confirmPassword,
            label = R.string.confirm_password,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = onDoneAction,
            onValueChange = { confirmPassword = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(
            onClick = { navController.navigate(LenBetaScreen.TeacherSignIn.route) },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.End)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(text = "Already have an account?")
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null
                )
            }
        }
        SignUpButton(
            text = R.string.sign_up, onSignUpClick = {
            }
        )
    }
}

@Preview
@Composable
fun TeacherSignUpScreenPreview() {
    LenBetaAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TeacherSignUpScreen(rememberNavController())
        }
    }
}