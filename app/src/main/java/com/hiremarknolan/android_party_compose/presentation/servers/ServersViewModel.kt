package com.hiremarknolan.android_party_compose.presentation.servers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiremarknolan.android_party_compose.common.Resource
import com.hiremarknolan.android_party_compose.domain.storage.SessionManager
import com.hiremarknolan.android_party_compose.domain.use_case.server_use_case.GetServersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ServersViewModel @Inject constructor(
    private val getServersUseCase: GetServersUseCase,
    private val sessionManager: SessionManager
)  : ViewModel() {

    private val _state = MutableStateFlow(ServersState())
    val state: StateFlow<ServersState> = _state

    init {
        getServers()
    }

    private fun getServers() {
        getServersUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    if(result.data != null) {
                        _state.value = ServersState(servers = result.data)
                    }
                }
                is Resource.Error -> {
                    _state.value = ServersState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ServersState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun logout() {
        sessionManager.clearAuthToken()
    }

}