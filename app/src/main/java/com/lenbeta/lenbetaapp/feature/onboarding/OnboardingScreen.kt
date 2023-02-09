package com.lenbeta.lenbetaapp.feature.onboarding

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.lenbeta.lenbetaapp.core.ui.components.NextButton
import com.lenbeta.lenbetaapp.core.ui.components.PagerIndicator
import com.lenbeta.lenbetaapp.core.ui.components.SkipButton

@Composable
fun OnboardingRoute(
    modifier: Modifier = Modifier,
    onOnboardingFinish: () -> Unit,
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {
    OnboardingScreen(
        onOnboardingFinish = {
            onBoardingViewModel.saveOnboardingState(completed = true)
            onOnboardingFinish.invoke()
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    onOnboardingFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pages = listOf(
        OnBoardingPage.FirstPage,
        OnBoardingPage.SecondPage,
    )
    val pagerState = rememberPagerState(
        initialPage = 0
    )
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .then(modifier)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            HorizontalPager(
                count = 2,
                state = pagerState,
                verticalAlignment = Alignment.Top,
                modifier = Modifier.weight(1f)
            ) { position ->
                PagerScreen(
                    onBoardingPage = pages[position]
                )
            }
            /* HorizontalPagerIndicator(
             pagerState = pagerState,
             modifier = Modifier
                 .align(Alignment.CenterHorizontally)
         )*/
            PagerIndicator(
                pages = pages,
                currentPage = pagerState.currentPage,
                modifier = Modifier
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally)
            )
            NextButton(
                modifier = Modifier.fillMaxWidth(),
                pagerState = pagerState,
                onNextButtonClick = onOnboardingFinish
            )
        }
        SkipButton(
            isVisible = (pagerState.currentPage != 1),
            onSkipClick = onOnboardingFinish
        )
    }
}

/*
val onFinish: (OnBoardingViewModel, NavHostController) -> Unit =
    { onBoardingViewModel, navController ->
        onBoardingViewModel.saveOnboardingState(completed = true)
        navController.navigate(
            LenBetaScreen.UserSelection.route,
            NavOptions.Builder()
                .setPopUpTo(LenBetaScreen.UserSelection.route, inclusive = true).build()
        )
    }
*/

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
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
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
    OnboardingScreen(
        onOnboardingFinish = {}
    )
}

@Preview
@Composable
fun FirstPagePreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        PagerScreen(onBoardingPage = OnBoardingPage.FirstPage)
    }
}
