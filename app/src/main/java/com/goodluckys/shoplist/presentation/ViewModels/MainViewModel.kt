package com.goodluckys.shoplist.presentation.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
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
import javax.inject.Inject

class MainViewModel @Inject constructor(
    getShopListUseCase: GetShopListUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val editItemUseCase: EditShopItemUseCase,
) : ViewModel() {

    val shopList = getShopListUseCase.getShopList()

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