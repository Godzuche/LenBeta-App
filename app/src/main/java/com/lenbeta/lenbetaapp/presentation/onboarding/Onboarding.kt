package com.lenbeta.lenbetaapp.presentation.onboarding

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.*
import com.lenbeta.lenbetaapp.presentation.theme.LenBetaAppTheme
import com.lenbeta.lenbetaapp.presentation.util.LenBetaScreen

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {
    LenBetaAppTheme {
        /*Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier
                .padding(all = 16.dp)
                .then(modifier)
        ) {
            NextButton {
                onBoardingViewModel.saveOnboardingState(completed = true)
//                navController.popBackStack()
                navController.navigate(
                    LenBetaScreen.UserSelection.route,
                    NavOptions.Builder()
                        .setPopUpTo(LenBetaScreen.UserSelection.route, inclusive = true).build()
                )
            }
            Introduction(modifier = Modifier.align(Alignment.Center))
        }*/
        val pages = listOf(
            OnBoardingPage.FirstPage,
            OnBoardingPage.SecondPage,
        )
        val pagerState = rememberPagerState()
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
                .then(modifier)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxSize()

            ) {
                HorizontalPager(
                    count = 2,
                    state = pagerState,
                    verticalAlignment = Alignment.Top,
                ) { position ->
                    PagerScreen(
                        onBoardingPage = pages[position],
                        modifier = Modifier
                            .fillMaxHeight(0.85f)
                            .fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(48.dp))
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }
            NextButton(
                pagerState = pagerState,
            ) {
                onBoardingViewModel.saveOnboardingState(completed = true)
//                navController.popBackStack()
                navController.navigate(
                    LenBetaScreen.UserSelection.route,
                    NavOptions.Builder()
                        .setPopUpTo(LenBetaScreen.UserSelection.route, inclusive = true).build()
                )
            }
        }
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(68.dp))
        Image(
            painter = painterResource(onBoardingPage.image),
            contentDescription = "Pager image",
            modifier = Modifier
                .size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = onBoardingPage.title),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = onBoardingPage.description),
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.wrapContentSize(Alignment.Center)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NextButton(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onNextButtonClick: () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = pagerState.currentPage == 1
    ) {
        OutlinedButton(
            onClick = onNextButtonClick,
//            modifier = modifier
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

@Preview
@Composable
fun FirstPagePreview() {
    LenBetaAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            PagerScreen(onBoardingPage = OnBoardingPage.FirstPage)
        }
    }
}
