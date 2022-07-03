package com.goodluckys.shoplist.domain.usecases

import androidx.lifecycle.LiveData
import com.goodluckys.shoplist.domain.ShopListRepository
import com.goodluckys.shoplist.domain.ShopItem
import javax.inject.Inject

class GetShopListUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {
    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}