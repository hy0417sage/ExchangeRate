package com.hy0417sage.wirebarley

import com.hy0417sage.wirebarley.impl.ExchangeClientImpl
import com.hy0417sage.wirebarley.impl.ExchangeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DIModule {
    @Provides
    fun provideClient(): Retrofit {
        return ExchangeClientImpl.getExchangeRate()
    }

    @Singleton
    @Provides
    fun provideExchangeRepository(
        retrofit: Retrofit,
    ): ExchangeRepository {
        return ExchangeRepositoryImpl(retrofit)
    }
}