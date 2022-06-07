package com.goodluckys.shoplist.domain.usecases

import com.goodluckys.shoplist.domain.ShopListRepository
import com.goodluckys.shoplist.domain.shopitem

class EditShopItemUseCase(private val shopListRepository: ShopListRepository){
    fun edit(item: shopitem){
        shopListRepository.edit(item)
    }
}