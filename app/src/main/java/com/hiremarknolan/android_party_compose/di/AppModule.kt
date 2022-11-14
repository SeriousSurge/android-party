package com.hiremarknolan.android_party_compose.di

import com.hiremarknolan.android_party_compose.common.Constants
import com.hiremarknolan.android_party_compose.data.network.AuthInterceptor
import com.hiremarknolan.android_party_compose.data.remote.PartyTesonetApi
import com.hiremarknolan.android_party_compose.data.repository.LoginRepositoryImpl
import com.hiremarknolan.android_party_compose.data.repository.ServersRepositoryImpl
import com.hiremarknolan.android_party_compose.domain.repository.LoginRepository
import com.hiremarknolan.android_party_compose.domain.repository.ServersRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTesonetApi(
        retrofit: Retrofit
    ): PartyTesonetApi {
        return retrofit.create(PartyTesonetApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: AuthInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

    @Provides
    @Singleton
    internal fun provideAuthInterceptor(
        interceptor: AuthInterceptor
    ): Interceptor = interceptor

    @Provides
    @Singleton
    fun provideServersRepository(api: PartyTesonetApi): ServersRepository {
        return ServersRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesLoginApi(api: PartyTesonetApi): LoginRepository {
        return LoginRepositoryImpl(api)
    }

}