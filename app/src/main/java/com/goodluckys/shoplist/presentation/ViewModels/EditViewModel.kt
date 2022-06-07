package com.goodluckys.shoplist.presentation.ViewModels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goodluckys.shoplist.data.ShopListRepoImpl
import com.goodluckys.shoplist.domain.usecases.AddShopListUseCase
import com.goodluckys.shoplist.domain.usecases.EditShopItemUseCase
import com.goodluckys.shoplist.domain.usecases.GetShopItemUseCase
import com.goodluckys.shoplist.domain.shopitem
import java.lang.Exception

class EditViewModel : ViewModel() {

    private val repository = ShopListRepoImpl()
    private val _errorInputName = MutableLiveData<Boolean>() // private set
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName
    private var _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _itemLD = MutableLiveData<shopitem>()
    val itemLD: LiveData<shopitem>
        get() = _itemLD

    private val _rFinish = MutableLiveData<Boolean>()

    val rFinish: LiveData<Boolean>
        get() {
            return _rFinish
        }


    private val AddShopListUseCase = AddShopListUseCase(repository)
    private val GetShopItemUseCase = GetShopItemUseCase(repository)
    private val EditShopItemUseCase = EditShopItemUseCase(repository)

    fun getItem(id: Int) {
        val item = GetShopItemUseCase.getItem(id)
        _itemLD.value = item
    }

    fun addItem(itemName: String?, itemCount: String?) {
        val name = parseName(itemName)
        val count = parseCount(itemCount)
        val valid = validate(name, count)
        if (valid) {
            val item = shopitem(name, count, true)
            AddShopListUseCase.add(item)
            finishWork()
        }
    }

    fun editItem(itemName: String?, itemCount: String?) {
        val name = parseName(itemName)
        val count = parseCount(itemCount)
        val valid = validate(name, count)
        if (valid) {
            itemLD.value?.let {
                val item = it.copy(name = name, count = count, Enabled = true)
                EditShopItemUseCase.edit(item)
                finishWork()
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

    private fun finishWork() {
        _rFinish.value = true
    }

}