package com.goodluckys.shoplist.presentation.ViewModels

import androidx.lifecycle.*
import com.goodluckys.shoplist.domain.usecases.AddShopListUseCase
import com.goodluckys.shoplist.domain.usecases.EditShopItemUseCase
import com.goodluckys.shoplist.domain.usecases.GetShopItemUseCase
import com.goodluckys.shoplist.domain.ShopItem
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class EditViewModel @Inject constructor(
    private val addShopListUseCase: AddShopListUseCase,
    private val getShopItemUseCase: GetShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase,
) : ViewModel() {


    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName
    private var _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _itemLD = MutableLiveData<ShopItem>()
    val itemLD: LiveData<ShopItem>
        get() = _itemLD

    private val _shouldCloseScreen = MutableLiveData<Boolean>()

    val shouldCloseScreen: LiveData<Boolean> // single life event  // scope для фрагмента
        get() {
            return _shouldCloseScreen
        }

    fun getItem(id: Int) {
        viewModelScope.launch {
            val item = getShopItemUseCase.getItem(id)
            _itemLD.postValue(item)
        }

    }

    fun addItem(itemName: String?, itemCount: String?) {
        val name = parseName(itemName)
        val count = parseCount(itemCount)
        val valid = validate(name, count)
        if (valid) {
            viewModelScope.launch {
                val item = ShopItem(0, name, count, true)
                addShopListUseCase.add(item)
                finishWork()
            }
        }
    }

    fun editItem(itemName: String?, itemCount: String?) {
        val name = parseName(itemName)
        val count = parseCount(itemCount)
        val valid = validate(name, count)
        if (valid) {
            _itemLD.value?.let {
                val item = it.copy(name = name, count = count)
                viewModelScope.launch {
                    editShopItemUseCase.edit(item)
                    finishWork()
                }
            }
        }

    }

    private fun parseName(itemName: String?): String {
        return itemName?.trim() ?: ""
    }

    private fun parseCount(itemCount: String?): Int {
        return try {
            itemCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validate(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            result = false
            _errorInputName.value = true
        }
        if (count <= 0) {
            result = false
            _errorInputCount.value = true
        }
        return result
    }


    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.postValue(true)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}