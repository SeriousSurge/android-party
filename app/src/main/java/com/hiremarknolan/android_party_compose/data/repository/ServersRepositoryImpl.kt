package com.hiremarknolan.android_party_compose.data.repository

import com.hiremarknolan.android_party_compose.data.remote.PartyTesonetApi
import com.hiremarknolan.android_party_compose.data.remote.dto.ServerDto
import com.hiremarknolan.android_party_compose.domain.model.Server
import com.hiremarknolan.android_party_compose.domain.repository.ServersRepository
import javax.inject.Inject

class ServersRepositoryImpl @Inject constructor(
    private val api: PartyTesonetApi
) : ServersRepository {

    override suspend fun getServers(): List<ServerDto> {
        return api.getServers()
    }
}
