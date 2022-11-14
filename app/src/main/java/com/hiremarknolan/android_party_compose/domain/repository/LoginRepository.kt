package com.hiremarknolan.android_party_compose.domain.repository

import com.hiremarknolan.android_party_compose.data.remote.dto.CredentialsDto
import com.hiremarknolan.android_party_compose.data.remote.dto.UserTokenDto

interface LoginRepository {
    suspend fun login(login: CredentialsDto): UserTokenDto
}
