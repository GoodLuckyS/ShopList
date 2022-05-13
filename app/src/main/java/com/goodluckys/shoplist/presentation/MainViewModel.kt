package com.goodluckys.shoplist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goodluckys.shoplist.data.ShopListRepoImpl
import com.goodluckys.shoplist.domain.DeleteItemUseCase
import com.goodluckys.shoplist.domain.EditShopItemUseCase
import com.goodluckys.shoplist.domain.GetShopListUseCase
import com.goodluckys.shoplist.domain.shopitem
import java.lang.RuntimeException

class MainViewModel : ViewModel() {

    private val repository = ShopListRepoImpl // не совсем верно

    private val showListUseCase = GetShopListUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)
    private val editItemUseCase = EditShopItemUseCase(repository)

    val shopList = showListUseCase.getShopList()



    fun deleteItemList(item:shopitem){
        deleteItemUseCase.delete(item)
    }

    fun changeEnableState(item:shopitem) {
        val newItem = item.copy(Enabled = !item.Enabled)
        editItemUseCase.edit(item)
    }

}