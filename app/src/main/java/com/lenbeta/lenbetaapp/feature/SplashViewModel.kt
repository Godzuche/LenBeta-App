package com.lenbeta.lenbetaapp.feature

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lenbeta.lenbetaapp.data.repository.DatastoreRepository
import com.lenbeta.lenbetaapp.feature.onboarding.navigation.OnboardingDestination
import com.lenbeta.lenbetaapp.feature.user.navigation.UserDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) : ViewModel() {

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination = mutableStateOf(OnboardingDestination.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            datastoreRepository.readOnBoardingState().collect { completed ->
                if (completed) {
                    _startDestination.value = UserDestination.route
                } else {
                    _startDestination.value = OnboardingDestination.route
                }
                _isLoading.value = false
            }
        }
    }

}