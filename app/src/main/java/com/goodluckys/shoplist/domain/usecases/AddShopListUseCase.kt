package com.goodluckys.shoplist.domain.usecases

import com.goodluckys.shoplist.domain.ShopListRepository
import com.goodluckys.shoplist.domain.ShopItem
import javax.inject.Inject

class AddShopListUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {
    suspend fun add(item: ShopItem) {
        shopListRepository.add(item)
    }
}