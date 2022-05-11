package com.goodluckys.shoplist.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun getItem(ItemId:Int) : shopitem {
        return shopListRepository.getItem(ItemId)
    }
}