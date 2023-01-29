package com.lenbeta.lenbetaapp.feature.home.student.explore.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lenbeta.lenbetaapp.feature.home.student.explore.ExploreRoute

const val exploreRoute = "explore_route"

fun NavGraphBuilder.exploreScreen() {
    composable(route = exploreRoute) {
        ExploreRoute()
    }
}