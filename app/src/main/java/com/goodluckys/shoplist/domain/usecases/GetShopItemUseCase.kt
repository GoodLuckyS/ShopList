package com.goodluckys.shoplist.domain.usecases

import com.goodluckys.shoplist.domain.ShopListRepository
import com.goodluckys.shoplist.domain.ShopItem

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun getItem(ItemId:Int) : ShopItem {
        return shopListRepository.getItem(ItemId)
    }
}