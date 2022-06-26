package com.lenbeta.lenbetaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.lenbeta.lenbetaapp.core.ui.theme.LenBetaAppTheme
import com.lenbeta.lenbetaapp.feature.LenBetaApp
import com.lenbeta.lenbetaapp.feature.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //    @Inject
//    lateinit var splashViewModel: SplashViewModel
    val splashViewModel: SplashViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            val isLoading by splashViewModel.isLoading
            isLoading
        }
        super.onCreate(savedInstanceState)
        setContent {
            LenBetaAppTheme {
                val startScreen by splashViewModel.startDestination
                LenBetaApp(
                    startDestination = startScreen
                )
            }
        }
    }
}