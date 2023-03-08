package com.hy0417sage.wirebarley.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hy0417sage.wirebarley.data.model.CacheEntity

@Dao
interface CacheDao {
    @Query("SELECT * FROM Cache")
    fun getCache(): CacheEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCache(cache: CacheEntity)
}