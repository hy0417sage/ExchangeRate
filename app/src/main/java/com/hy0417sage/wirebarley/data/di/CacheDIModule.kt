package com.hy0417sage.wirebarley.data.di

import android.content.Context
import androidx.room.Room
import com.hy0417sage.wirebarley.data.CacheRepositoryImpl
import com.hy0417sage.wirebarley.data.db.CacheDao
import com.hy0417sage.wirebarley.data.db.CacheDatabase
import com.hy0417sage.wirebarley.domain.CacheRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CacheDIModule {
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
    fun provideCacheDao(appDataBase: CacheDatabase): CacheDao {
        return appDataBase.cacheDao()
    }
}