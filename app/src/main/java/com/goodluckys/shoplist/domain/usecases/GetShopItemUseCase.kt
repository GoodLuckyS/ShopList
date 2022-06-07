package com.goodluckys.shoplist.domain.usecases

import com.goodluckys.shoplist.domain.ShopListRepository
import com.goodluckys.shoplist.domain.shopitem

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun getItem(ItemId:Int) : shopitem {
        return shopListRepository.getItem(ItemId)
    }
}