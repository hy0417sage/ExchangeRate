package com.hy0417sage.wirebarley

import androidx.lifecycle.LiveData
import com.hy0417sage.wirebarley.cache.CacheEntity

interface CacheRepository {
    fun getCache(): LiveData<CacheEntity>
    suspend fun insertCache(cache: CacheEntity)
}
