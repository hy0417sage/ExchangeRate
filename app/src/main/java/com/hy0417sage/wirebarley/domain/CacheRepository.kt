package com.hy0417sage.wirebarley.domain

import com.hy0417sage.wirebarley.data.model.CacheEntity
import kotlinx.coroutines.flow.Flow

interface CacheRepository {
    fun getCache(): Flow<CacheEntity>
    suspend fun updateCache(cache: CacheEntity)
}
