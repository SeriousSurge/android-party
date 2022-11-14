package com.hiremarknolan.android_party_compose.domain.use_case.server_use_case

import com.hiremarknolan.android_party_compose.domain.storage.SessionManager
import javax.inject.Inject

class GetLogoutUseCase @Inject constructor(
    private val sessionManager: SessionManager
){
    fun logout() = sessionManager.clearAuthToken()
}