package com.goodluckys.shoplist.presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.goodluckys.shoplist.domain.usecases.AddShopListUseCase
import com.goodluckys.shoplist.domain.usecases.EditShopItemUseCase
import com.goodluckys.shoplist.domain.usecases.GetShopItemUseCase
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class EditViewModelFactory @Inject constructor(
    private val addShopListUseCase: AddShopListUseCase,
    private val getShopItemUseCase: GetShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditViewModel(addShopListUseCase, getShopItemUseCase, editShopItemUseCase) as T
    }
}