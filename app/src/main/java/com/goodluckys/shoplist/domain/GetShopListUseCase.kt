package com.goodluckys.shoplist.domain

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopList(): List<shopitem>{
        return shopListRepository.getShopList()
    }
}