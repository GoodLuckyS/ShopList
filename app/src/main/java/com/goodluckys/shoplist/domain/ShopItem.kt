package com.goodluckys.shoplist.domain

data class ShopItem(
    var id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean,
)
