package com.hy0417sage.wirebarley.data.di

import com.hy0417sage.wirebarley.data.api.ExchangeClientImpl
import com.hy0417sage.wirebarley.data.ExchangeRepositoryImpl
import com.hy0417sage.wirebarley.domain.ExchangeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkDIModule {
    @Provides
    fun provideClient(): Retrofit {
        return ExchangeClientImpl.retrofit()
    }

    @Singleton
    @Provides
    fun provideExchangeRepository(
        retrofit: Retrofit,
    ): ExchangeRepository {
        return ExchangeRepositoryImpl(retrofit)
    }
}