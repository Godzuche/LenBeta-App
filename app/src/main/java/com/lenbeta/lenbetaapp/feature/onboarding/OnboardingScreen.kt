package com.lenbeta.lenbetaapp.feature.onboarding

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@Composable
fun OnboardingRoute(
    modifier: Modifier = Modifier,
    onOnboardingFinish: () -> Unit
) {
    OnboardingScreen(
        onOnboardingFinish = onOnboardingFinish,
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
            pagerState = pagerState,
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
fun PagerIndicator(
    pages: List<OnBoardingPage>,
    currentPage: Int,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(all = 16.dp)
            .then(modifier)
    ) {
        repeat(pages.size) {
            Indicator(
                isSelected = (it == currentPage),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean, color: Color) {
    val width by animateDpAsState(
        targetValue = if (isSelected) 24.dp else 8.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(4.dp)
            .width(width)
            .clip(CircleShape)
            .background(
                if (isSelected) color else Color.Gray.copy(alpha = 0.5f)
            )
    )
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
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
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
        visible = (pagerState.currentPage == 1),
        enter = expandIn(expandFrom = Alignment.Center),
        exit = shrinkOut(shrinkTowards = Alignment.Center),
        label = "Next Button"
    ) {
        OutlinedButton(
            onClick = onNextButtonClick
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Next")
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SkipButton(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onSkipClick: () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = (pagerState.currentPage != 1),
        enter = expandHorizontally(),
        exit = shrinkHorizontally(),
        label = "Skip Button"
    ) {
        TextButton(onClick = onSkipClick) {
            Text("Skip")
        }
    }
}

@Preview
@Composable
fun IndicatorPreview() {
    PagerIndicator(
        pages = listOf(
            OnBoardingPage.FirstPage,
            OnBoardingPage.SecondPage,
        ), currentPage = 0, modifier = Modifier
    )
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
