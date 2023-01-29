package com.lenbeta.lenbetaapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lenbeta.lenbetaapp.MainActivityUiState.Loading
import com.lenbeta.lenbetaapp.MainActivityUiState.Success
import com.lenbeta.lenbetaapp.data.repository.DatastoreRepository
import com.lenbeta.lenbetaapp.feature.auth.navigation.usersAuthGraphRoute
import com.lenbeta.lenbetaapp.feature.onboarding.navigation.onboardingRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    datastoreRepository: DatastoreRepository
) : ViewModel() {

    val uiState: StateFlow<MainActivityUiState> = datastoreRepository
        .readOnBoardingState().map { completed ->
            Success(completed.getStartDestinationRoute())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Loading
        )

}

fun Boolean.getStartDestinationRoute(): String = if (this) usersAuthGraphRoute else onboardingRoute

sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(val startDestination: String) : MainActivityUiState
}
