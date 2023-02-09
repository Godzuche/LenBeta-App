package com.lenbeta.lenbetaapp.feature.explore.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import com.lenbeta.lenbetaapp.feature.explore.ExploreRoute

const val exploreRoute = "explore_route"

fun NavController.navigateToExplore(navOptions: NavOptions? = null) {
    this.navigate(exploreRoute, navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.exploreScreen() {
    composable(route = exploreRoute) {
        ExploreRoute()
    }
}