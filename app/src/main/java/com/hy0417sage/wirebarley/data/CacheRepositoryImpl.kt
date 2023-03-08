package com.hy0417sage.wirebarley.data

import com.hy0417sage.wirebarley.domain.CacheRepository
import com.hy0417sage.wirebarley.data.db.CacheDao
import com.hy0417sage.wirebarley.data.model.CacheEntity
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor(
    private val cacheDao: CacheDao,
) : CacheRepository {
    override fun getCache(): CacheEntity = cacheDao.getCache()
    override suspend fun updateCache(cache: CacheEntity) {
        cacheDao.updateCache(cache)
    }
}