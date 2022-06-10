package com.lenbeta.lenbetaapp.presentation.student

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lenbeta.lenbetaapp.presentation.theme.LenBetaAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentSignInScreen() {
    LenBetaAppTheme {
        Scaffold(
            topBar = {
                SmallTopAppBar(
                    title = {
                        Text(text = "Student Sign In")
                    }
                )
            }
        ) { innerPadding ->
            StudentSignInScreenContent(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun StudentSignInScreenContent(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.then(modifier))
}

@Preview
@Composable
fun StudentSignInScreenPreview() {
    LenBetaAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            StudentSignInScreen()
        }
    }
}