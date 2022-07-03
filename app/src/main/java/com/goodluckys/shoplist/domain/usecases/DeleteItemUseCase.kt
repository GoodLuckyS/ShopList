package com.goodluckys.shoplist.domain.usecases

import com.goodluckys.shoplist.domain.ShopListRepository
import com.goodluckys.shoplist.domain.ShopItem
import javax.inject.Inject

class DeleteItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {
    suspend fun delete(item: ShopItem) {
        shopListRepository.delete(item)
    }
}