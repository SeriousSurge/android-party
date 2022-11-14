package com.hiremarknolan.android_party_compose.domain.use_case.server_use_case

import com.hiremarknolan.android_party_compose.common.Resource
import com.hiremarknolan.android_party_compose.data.remote.dto.toServer
import com.hiremarknolan.android_party_compose.domain.model.Server
import com.hiremarknolan.android_party_compose.domain.repository.ServersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetServersUseCase @Inject constructor(
    private val repository: ServersRepository
) {
    operator fun invoke(): Flow<Resource<List<Server>>> = flow {
        try {
            emit(Resource.Loading())
            val servers = repository.getServers().map { it.toServer() }
            emit(Resource.Success(servers))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }.distinctUntilChanged()
}
