package com.hiremarknolan.android_party_compose.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.hiremarknolan.android_party_compose.domain.model.UserToken
import kotlinx.serialization.Serializable

@Serializable
data class UserTokenDto(
    @SerializedName("token")
    val token: String
)

fun UserTokenDto.toUserToken(): UserToken {
    return UserToken(
        token = token
    )
}