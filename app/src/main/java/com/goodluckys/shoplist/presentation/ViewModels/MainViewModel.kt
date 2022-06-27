package com.goodluckys.shoplist.presentation.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.goodluckys.shoplist.data.room.RoomShopListRepoImpl
import com.goodluckys.shoplist.domain.*
import com.goodluckys.shoplist.domain.usecases.DeleteItemUseCase
import com.goodluckys.shoplist.domain.usecases.EditShopItemUseCase
import com.goodluckys.shoplist.domain.usecases.GetShopListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RoomShopListRepoImpl(application)

    private val showListUseCase = GetShopListUseCase(repository)
    private val deleteItemUseCase = DeleteItemUseCase(repository)
    private val editItemUseCase = EditShopItemUseCase(repository)

    val shopList = showListUseCase.getShopList()

    fun deleteItemList(item: ShopItem) {
        viewModelScope.launch {
            deleteItemUseCase.delete(item)
        }
    }

    fun changeEnableState(item: ShopItem) {
        val newItem = item.copy(enabled = !item.enabled)
        viewModelScope.launch {
            editItemUseCase.edit(newItem)
        }

    }


}