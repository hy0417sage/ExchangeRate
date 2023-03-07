package com.hy0417sage.wirebarley.cache

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CacheDao {
    @Query("SELECT * FROM Cache")
    fun getCache(): Flow<CacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCache(cache: CacheEntity)
}