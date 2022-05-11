package com.goodluckys.shoplist.domain

class EditShopItemUseCase(private val shopListRepository: ShopListRepository){
    fun edit(item:shopitem){
        shopListRepository.edit(item)
    }
}