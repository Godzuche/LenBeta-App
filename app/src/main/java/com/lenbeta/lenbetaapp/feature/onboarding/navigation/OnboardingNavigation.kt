package com.lenbeta.lenbetaapp.feature.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.lenbeta.lenbetaapp.core.navigation.LenBetaNavigationDestination
import com.lenbeta.lenbetaapp.feature.onboarding.OnboardingRoute
import com.lenbeta.lenbetaapp.feature.onboarding.OnboardingScreen

object OnboardingDestination : LenBetaNavigationDestination {
    override val route = "onboarding_route"
    override val destination = "onboarding_navigation"
}

fun NavGraphBuilder.onboardingGraph(
    onOnboardingFinish: () -> Unit
) {
    composable(route = OnboardingDestination.route) {
        OnboardingRoute(onOnboardingFinish = onOnboardingFinish)
    }
}