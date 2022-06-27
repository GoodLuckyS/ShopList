package com.goodluckys.shoplist.domain.usecases

import com.goodluckys.shoplist.domain.ShopListRepository
import com.goodluckys.shoplist.domain.ShopItem

class EditShopItemUseCase(private val shopListRepository: ShopListRepository){
    suspend fun edit(item: ShopItem){
        shopListRepository.edit(item)
    }
}