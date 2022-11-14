package com.hiremarknolan.android_party_compose.domain.repository

import com.hiremarknolan.android_party_compose.data.remote.dto.ServerDto
import com.hiremarknolan.android_party_compose.domain.model.Server

interface ServersRepository {
    suspend fun getServers(): List<ServerDto>
}