package com.lenbeta.lenbetaapp.feature.student.student_auth

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lenbeta.lenbetaapp.core.ui.theme.LenBetaAppTheme


@Composable
fun StudentSignInRoute() {
    StudentSignInScreen()
}

@Composable
fun StudentSignInScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.then(modifier))
}

@Preview
@Composable
fun StudentSignInScreenPreview() {
    LenBetaAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            StudentSignInRoute()
        }
    }
}