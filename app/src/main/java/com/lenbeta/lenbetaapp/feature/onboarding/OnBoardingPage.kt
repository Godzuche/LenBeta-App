package com.lenbeta.lenbetaapp.feature.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.lenbeta.lenbetaapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    @StringRes
    val title: Int,
    @StringRes
    val description: Int
) {
    object FirstPage : OnBoardingPage(
        image = R.drawable.ic_launcher_background,
        title = R.string.productivity,
        description = R.string.dummy_text
    )

    object SecondPage : OnBoardingPage(
        image = R.drawable.ic_launcher_background,
        title = R.string.communication,
        description = R.string.dummy_text
    )
}
