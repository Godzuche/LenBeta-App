package com.lenbeta.lenbetaapp.feature.auth.teacher_auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lenbeta.lenbetaapp.core.ui.theme.LenBetaAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherSignInScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Teacher Sign In")
                }
            )
        }
    ) { innerPadding ->
        TeacherSignInScreenContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun TeacherSignInScreenContent(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.then(modifier))
}

@Preview
@Composable
fun TeacherSignInScreenPreview() {
    LenBetaAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TeacherSignInScreen()
        }
    }
}