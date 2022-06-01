package com.lenbeta.lenbetaapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lenbeta.lenbetaapp.data.DatastoreRepository
import com.lenbeta.lenbetaapp.presentation.util.LenBetaScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) : ViewModel() {

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination = mutableStateOf(LenBetaScreen.Welcome.route)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            datastoreRepository.readOnBoardingState().collect { completed ->
                if (completed) {
                    _startDestination.value = LenBetaScreen.UserSelection.route
                } else {
                    _startDestination.value = LenBetaScreen.Welcome.route
                }
                _isLoading.value = false
            }
        }
    }

}