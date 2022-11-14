package com.hiremarknolan.android_party_compose.data.remote

import com.hiremarknolan.android_party_compose.common.Constants.CONTENT_TYPE_HEADER
import com.hiremarknolan.android_party_compose.common.Constants.TOKENS_ENDPOINT
import com.hiremarknolan.android_party_compose.data.remote.dto.CredentialsDto
import com.hiremarknolan.android_party_compose.data.remote.dto.ServerDto
import com.hiremarknolan.android_party_compose.data.remote.dto.UserTokenDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface PartyTesonetApi {

    @GET("servers")
    suspend fun getServers(): List<ServerDto>

    @POST(TOKENS_ENDPOINT)
    @Headers(CONTENT_TYPE_HEADER)
    suspend fun login(@Body login: CredentialsDto): UserTokenDto

}