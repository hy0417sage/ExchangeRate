package com.hy0417sage.wirebarley.impl

import androidx.lifecycle.LiveData
import com.hy0417sage.wirebarley.CacheRepository
import com.hy0417sage.wirebarley.cache.CacheDao
import com.hy0417sage.wirebarley.cache.CacheEntity
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor(private val cacheDao: CacheDao) : CacheRepository {
    override fun getCache(): LiveData<CacheEntity> = cacheDao.getCache()

    override suspend fun insertCache(cache: CacheEntity) {
        cacheDao.insertCache(cache)
    }
}


