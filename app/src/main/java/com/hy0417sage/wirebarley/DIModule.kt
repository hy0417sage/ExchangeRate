package com.hy0417sage.wirebarley

import android.content.Context
import androidx.room.Room
import com.hy0417sage.wirebarley.cache.CacheDao
import com.hy0417sage.wirebarley.cache.CacheDatabase
import com.hy0417sage.wirebarley.impl.CacheRepositoryImpl
import com.hy0417sage.wirebarley.impl.ExchangeClientImpl
import com.hy0417sage.wirebarley.impl.ExchangeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideCacheRepository(
        cacheDao: CacheDao,
    ): CacheRepository {
        return CacheRepositoryImpl(cacheDao)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): CacheDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CacheDatabase::class.java,
            "Cache.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideANoteDao(appDataBase: CacheDatabase): CacheDao {
        return appDataBase.cacheDao()
    }
}