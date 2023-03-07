package com.hy0417sage.wirebarley.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hy0417sage.wirebarley.data.model.CacheEntity

@Database(entities = [CacheEntity::class], version = 1)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun cacheDao(): CacheDao
}