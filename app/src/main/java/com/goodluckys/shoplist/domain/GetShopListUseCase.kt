package com.goodluckys.shoplist.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopList(): LiveData<List<shopitem>>{
            return shopListRepository.getShopList()
    }
}