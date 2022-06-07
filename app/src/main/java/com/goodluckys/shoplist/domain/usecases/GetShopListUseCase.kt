package com.goodluckys.shoplist.domain.usecases

import androidx.lifecycle.LiveData
import com.goodluckys.shoplist.domain.ShopListRepository
import com.goodluckys.shoplist.domain.shopitem

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopList(): LiveData<List<shopitem>> {
        return shopListRepository.getShopList()
    }
}