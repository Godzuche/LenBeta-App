package com.lenbeta.lenbetaapp.presentation.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lenbeta.lenbetaapp.presentation.util.LenBetaScreen
import com.lenbeta.lenbetaapp.presentation.theme.LenBetaAppTheme

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {
    LenBetaAppTheme {
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier
                .padding(all = 16.dp)
                .then(modifier)
        ) {
            NextButton {
                onBoardingViewModel.saveOnboardingState(completed = true)
                navController.popBackStack()
                navController.navigate(
                    LenBetaScreen.UserSelection.route
                )
            }
            Introduction(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun NextButton(
    modifier: Modifier = Modifier,
    onNextButtonClick: () -> Unit
) {
    OutlinedButton(
        onClick = onNextButtonClick,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
            )
            Text(text = "Next")
        }
    }
}

@Composable
fun Introduction(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Welcome to LenBeta App",
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Let's help manage your learning system for you and make you more productive in a very efficient way",
            textAlign = TextAlign.Center
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "OnboardingScreenDarkPreview"
)
@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    LenBetaAppTheme {
        WelcomeScreen(navController = rememberNavController())
    }
}
