package com.lenbeta.lenbetaapp.feature.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lenbeta.lenbetaapp.feature.onboarding.OnboardingRoute

const val onboardingRoute = "onboarding_route"

fun NavGraphBuilder.onboardingScreen(
    onOnboardingFinish: () -> Unit
) {
    composable(route = onboardingRoute) {
        OnboardingRoute(onOnboardingFinish = onOnboardingFinish)
    }
}