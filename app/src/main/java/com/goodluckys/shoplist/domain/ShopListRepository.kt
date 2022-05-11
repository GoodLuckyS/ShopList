package com.goodluckys.shoplist.domain

interface ShopListRepository {

    fun getShopList(): List<shopitem>

    fun getItem(ItemId:Int): shopitem

    fun add(item:shopitem)

    fun delete(item : shopitem)

    fun edit(item:shopitem)

}