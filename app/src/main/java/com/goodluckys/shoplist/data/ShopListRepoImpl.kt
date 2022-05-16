package com.goodluckys.shoplist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.goodluckys.shoplist.domain.ShopListRepository
import com.goodluckys.shoplist.domain.shopitem
import java.lang.RuntimeException
import kotlin.random.Random

object ShopListRepoImpl: ShopListRepository {   // наследовались от интерфейса(репозитория),для реализации функций

    private var shopList = mutableListOf<shopitem>() // Создали список,в котором будем хранить что-то
    private var autoID = 0
    private val shopListLD = MutableLiveData<List<shopitem>>()

    init {
        for(i in 0 until 20){
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
    }

    override fun getItem(ItemId: Int): shopitem {
        return shopList.find { it.id == ItemId } ?: throw RuntimeException("Элемента с номером $ItemId не найдено :(")
    }

    override fun add(item: shopitem) {
        if(item.id == shopitem.UNDEF) {  // Почитать
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