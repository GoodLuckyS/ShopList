package com.goodluckys.shoplist.domain

class DeleteItemUseCase(private val shopListRepository: ShopListRepository) {
    fun delete(item : shopitem)  {
        shopListRepository.delete(item)
    }
}