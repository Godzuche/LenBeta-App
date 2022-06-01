package com.lenbeta.lenbetaapp.presentation.onboarding

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.lenbeta.lenbetaapp.presentation.theme.LenBetaAppTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onClickNext: () -> Unit = {},
//    onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {
    LenBetaAppTheme {
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
                modifier = Modifier.fillMaxSize()
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
            }
            NextButton(
                pagerState = pagerState,
                onNextButtonClick = onClickNext
            )
        }
    }
}

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
                isSelected = it == currentPage,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean, color: Color) {
    val width by animateDpAsState(
        targetValue = if (isSelected) 16.dp else 8.dp,
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
    LenBetaAppTheme {
        WelcomeScreen()
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
