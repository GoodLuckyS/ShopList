package com.goodluckys.shoplist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun getShopList(): LiveData<List<shopitem>>

    fun getItem(ItemId: Int): shopitem

    fun add(item: shopitem)

    fun delete(item: shopitem)

    fun edit(item: shopitem)

}