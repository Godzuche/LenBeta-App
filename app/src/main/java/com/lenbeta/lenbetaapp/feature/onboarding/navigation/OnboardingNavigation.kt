package com.lenbeta.lenbetaapp.feature.onboarding.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.lenbeta.lenbetaapp.feature.onboarding.OnboardingRoute

const val onboardingRoute = "onboarding_route"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.onboardingScreen(
    onOnboardingFinish: () -> Unit
) {
    composable(route = onboardingRoute) {
        OnboardingRoute(onOnboardingFinish = onOnboardingFinish)
    }
}