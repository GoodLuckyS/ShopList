package com.goodluckys.shoplist.domain.usecases

import com.goodluckys.shoplist.domain.ShopListRepository
import com.goodluckys.shoplist.domain.ShopItem

class AddShopListUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun add(item: ShopItem) {
        shopListRepository.add(item)
    }
}