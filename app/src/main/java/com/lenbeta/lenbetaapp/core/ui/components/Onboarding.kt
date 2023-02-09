package com.lenbeta.lenbetaapp.core.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.lenbeta.lenbetaapp.feature.onboarding.OnBoardingPage


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
    isVisible: Boolean,
    onSkipClick: () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible,
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
