package com.hy0417sage.wirebarley.impl

import com.hy0417sage.wirebarley.CacheRepository
import com.hy0417sage.wirebarley.cache.CacheDao
import com.hy0417sage.wirebarley.cache.CacheEntity
import javax.inject.Inject

class CacheRepositoryImpl @Inject constructor(private val cacheDao: CacheDao) : CacheRepository {
    override suspend fun getCache(): List<CacheEntity> {
        return try {
            cacheDao.getCache()
        } catch (e: Exception) {
            // 네트워크가 연결되지 않은 상황에서 데이터를 가져오는 방법을 정의
            listOf()
        }
    }

    override suspend fun insertCache(cache: List<CacheEntity>) {
        cacheDao.insertCache(cache)
    }
}
