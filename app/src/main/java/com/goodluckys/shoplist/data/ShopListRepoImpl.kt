package com.goodluckys.shoplist.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.goodluckys.shoplist.domain.ShopListRepository
import com.goodluckys.shoplist.domain.shopitem
import java.lang.RuntimeException
import kotlin.random.Random

class ShopListRepoImpl :
    ShopListRepository {   // наследовались от интерфейса(репозитория),для реализации функций,через DI class

    private var shopList =mutableListOf<shopitem>() // Создали список,в котором будем хранить что-то
    private var autoID = 0
    private val shopListLD = MutableLiveData<List<shopitem>>()  // добавить сортировку три сет

    init {
        for (i in 0 until 21) {
            val soap = shopitem(
                "Name is $i",
                120,
                Random.nextBoolean()
            )
            add(soap)
        }
    }

    override fun getShopList(): LiveData<List<shopitem>> {
        return shopListLD
        Log.d("Jopa","This is after getList ${shopListLD.value}")
    }

    override fun getItem(ItemId: Int): shopitem {
        return shopList.find { it.id == ItemId }
            ?: throw RuntimeException("Элемента с номером $ItemId не найдено :(") // TODO()
    }

    override fun add(item: shopitem) {
        if (item.id == shopitem.UNDEF) {  // class UUID
            item.id = autoID++
        }
        shopList.add(item)
        updateList()
    }

    override fun delete(item: shopitem) { // удаление элемента из списка
        shopList.remove(item)
        updateList()
    }

    override fun edit(item: shopitem) {  // получаем на вход готовый новый элемент,чтобы изменить список - удаляем старый,втыкаем новый
        val oldElement = getItem(item.id)
        shopList.remove(oldElement)
        add(item)
    }

    private fun updateList() {
        shopListLD.value = shopList.toList()
    }
}