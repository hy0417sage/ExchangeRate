package com.hy0417sage.wirebarley.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cache")
data class CacheEntity(
    @PrimaryKey val id: Int,
    val KRW: Double,
    val JPY: Double,
    val PHP: Double,
)