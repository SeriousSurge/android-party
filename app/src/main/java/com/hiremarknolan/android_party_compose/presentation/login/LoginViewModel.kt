package com.hiremarknolan.android_party_compose.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiremarknolan.android_party_compose.common.Resource
import com.hiremarknolan.android_party_compose.domain.model.UserCredentials
import com.hiremarknolan.android_party_compose.domain.model.UserToken
import com.hiremarknolan.android_party_compose.domain.storage.SessionManager
import com.hiremarknolan.android_party_compose.domain.use_case.login_use_case.GetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getLoginUseCase: GetLoginUseCase,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    init {
        val savedToken = sessionManager.fetchAuthToken()
        if (savedToken != null) _state.value = LoginState(token = UserToken(savedToken))
    }

    fun handleLoginEvent(credentials: UserCredentials) {
        _state.value = LoginState(
            credentials = credentials
        )
        login()
    }

    private fun login() {
        _state.value.credentials?.let { credentials ->
            getLoginUseCase(credentials).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            sessionManager.saveAuthToken(it.token)
                            _state.value = LoginState(token = it)
                        }
                    }

                    is Resource.Error -> {
                        _state.value = LoginState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }

                    is Resource.Loading -> {
                         LoginState(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}