package com.goodluckys.shoplist.domain.usecases

import com.goodluckys.shoplist.domain.ShopListRepository
import com.goodluckys.shoplist.domain.shopitem

class DeleteItemUseCase(private val shopListRepository: ShopListRepository) {
    fun delete(item : shopitem)  {
        shopListRepository.delete(item)
    }
}