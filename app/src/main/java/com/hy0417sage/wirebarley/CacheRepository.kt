package com.hy0417sage.wirebarley

import com.hy0417sage.wirebarley.cache.CacheEntity

interface CacheRepository {
    suspend fun getCache(): List<CacheEntity>
    suspend fun insertCache(cache: List<CacheEntity>)
}