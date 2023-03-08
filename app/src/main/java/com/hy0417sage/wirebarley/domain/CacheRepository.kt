package com.hy0417sage.wirebarley.domain

import com.hy0417sage.wirebarley.data.model.CacheEntity

interface CacheRepository {
    fun getCache(): CacheEntity
    suspend fun updateCache(cache: CacheEntity)
}
