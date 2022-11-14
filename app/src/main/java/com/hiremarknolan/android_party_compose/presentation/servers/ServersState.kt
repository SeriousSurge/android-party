package com.hiremarknolan.android_party_compose.presentation.servers

import com.hiremarknolan.android_party_compose.domain.model.Server

data class ServersState(
    val isLoading: Boolean = false,
    val servers: List<Server> = emptyList(),
    val error: String = "",
    val logout: Boolean = false
)
