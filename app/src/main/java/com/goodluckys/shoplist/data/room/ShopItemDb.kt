package com.goodluckys.shoplist.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "ShopItems"
)
data class ShopItemDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean,
)