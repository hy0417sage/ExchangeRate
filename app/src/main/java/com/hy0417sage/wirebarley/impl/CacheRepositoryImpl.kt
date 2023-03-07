package com.hy0417sage.wirebarley.impl

import androidx.lifecycle.LiveData
import com.hy0417sage.wirebarley.CacheRepository
import com.hy0417sage.wirebarley.cache.CacheDao
import com.hy0417sage.wirebarley.cache.CacheEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor(private val cacheDao: CacheDao) : CacheRepository {
    override fun getCache(): Flow<CacheEntity> = cacheDao.getCache()

    override suspend fun updateCache(cache: CacheEntity) {
        cacheDao.updateCache(cache)
    }
}


