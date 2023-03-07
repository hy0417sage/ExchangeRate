package com.hy0417sage.wirebarley.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cache")
data class CacheEntity(
    @PrimaryKey val USD: String = "미국(USD)",
    val KRW: Double,
    val JPY: Double,
    val PHP: Double,
)