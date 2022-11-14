package com.hiremarknolan.android_party_compose.data.repository

import com.hiremarknolan.android_party_compose.data.remote.PartyTesonetApi
import com.hiremarknolan.android_party_compose.data.remote.dto.CredentialsDto
import com.hiremarknolan.android_party_compose.data.remote.dto.UserTokenDto
import com.hiremarknolan.android_party_compose.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: PartyTesonetApi
) : LoginRepository {

    override suspend fun login(login: CredentialsDto): UserTokenDto = api.login(login)

}