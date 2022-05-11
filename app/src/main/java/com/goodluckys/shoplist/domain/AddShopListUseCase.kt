package com.goodluckys.shoplist.domain

class AddShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun add(item:shopitem) {
        shopListRepository.add(item)
    }
}