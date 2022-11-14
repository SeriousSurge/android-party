package com.hiremarknolan.android_party_compose.presentation.login

import com.hiremarknolan.android_party_compose.domain.model.UserCredentials
import com.hiremarknolan.android_party_compose.domain.model.UserToken

data class LoginState(
    val isLoading: Boolean = false,
    var credentials: UserCredentials? = null,
    val token: UserToken? = null,
    val error: String = ""
)