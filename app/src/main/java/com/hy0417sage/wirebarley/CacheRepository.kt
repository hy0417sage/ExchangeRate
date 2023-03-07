package com.hy0417sage.wirebarley

import com.hy0417sage.wirebarley.cache.CacheEntity
import kotlinx.coroutines.flow.Flow

interface CacheRepository {
    fun getCache(): Flow<CacheEntity>
    suspend fun updateCache(cache: CacheEntity)
}
