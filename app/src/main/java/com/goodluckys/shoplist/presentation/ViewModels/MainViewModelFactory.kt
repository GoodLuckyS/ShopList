package com.goodluckys.shoplist.presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.goodluckys.shoplist.domain.usecases.DeleteItemUseCase
import com.goodluckys.shoplist.domain.usecases.EditShopItemUseCase
import com.goodluckys.shoplist.domain.usecases.GetShopListUseCase
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")

class MainViewModelFactory @Inject constructor(
    private val getShopListUseCase: GetShopListUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val editItemUseCase: EditShopItemUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getShopListUseCase, deleteItemUseCase, editItemUseCase) as T
    }
}