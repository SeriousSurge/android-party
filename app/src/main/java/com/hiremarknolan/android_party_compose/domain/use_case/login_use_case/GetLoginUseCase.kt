package com.hiremarknolan.android_party_compose.domain.use_case.login_use_case

import com.hiremarknolan.android_party_compose.common.Resource
import com.hiremarknolan.android_party_compose.data.remote.dto.toCredentialsDto
import com.hiremarknolan.android_party_compose.data.remote.dto.toUserToken
import com.hiremarknolan.android_party_compose.domain.model.UserCredentials
import com.hiremarknolan.android_party_compose.domain.model.UserToken
import com.hiremarknolan.android_party_compose.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    operator fun invoke(credentials: UserCredentials): Flow<Resource<UserToken>> = flow {
        try {
            emit(Resource.Loading<UserToken>())
            val token = repository.login(credentials.toCredentialsDto()).toUserToken()
            emit(Resource.Success<UserToken>(token))
        } catch(e: HttpException) {
            emit(Resource.Error<UserToken>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<UserToken>("Couldn't reach server. Check your internet connection."))
        }
    }
}
