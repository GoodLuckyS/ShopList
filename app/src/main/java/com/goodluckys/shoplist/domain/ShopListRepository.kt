package com.goodluckys.shoplist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

     fun getShopList(): LiveData<List<ShopItem>>

     suspend fun getItem(ItemId: Int): ShopItem

     suspend fun add(item: ShopItem)

     suspend fun delete(item: ShopItem)

     suspend fun edit(item: ShopItem)

}