package com.goodluckys.shoplist.domain.usecases

import com.goodluckys.shoplist.domain.ShopListRepository
import com.goodluckys.shoplist.domain.shopitem

class AddShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun add(item: shopitem) {
        shopListRepository.add(item)
    }
}