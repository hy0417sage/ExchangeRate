package com.hy0417sage.wirebarley.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cache")
data class CacheEntity(
    @PrimaryKey val id: Int? = null,
    val KRW: Double = 0.0,
    val JPY: Double = 0.0,
    val PHP: Double = 0.0
)