package com.lenbeta.lenbetaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lenbeta.lenbetaapp.navigation.SetUpNavGraph
import com.lenbeta.lenbetaapp.presentation.SplashViewModel
import com.lenbeta.lenbetaapp.presentation.theme.LenBetaAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            val isLoading by splashViewModel.isLoading
            !isLoading
        }
        setContent {
            LenBetaAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val startScreen by splashViewModel.startDestination
                    val navController = rememberNavController()
                    LenBetaApp(
                        startDestination = startScreen,
                        navController = navController
                    )
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun LenBetaApp(startDestination: String, navController: NavHostController) {
    LenBetaAppTheme {
        SetUpNavGraph(startDestination = startDestination, navController = navController)
    }
}
