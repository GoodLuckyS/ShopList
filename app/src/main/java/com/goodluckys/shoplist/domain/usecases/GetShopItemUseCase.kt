package com.goodluckys.shoplist.domain.usecases

import com.goodluckys.shoplist.domain.ShopListRepository
import com.goodluckys.shoplist.domain.ShopItem
import javax.inject.Inject

class GetShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {
    suspend fun getItem(ItemId: Int): ShopItem {
        return shopListRepository.getItem(ItemId)
    }
}