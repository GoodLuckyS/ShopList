package com.goodluckys.shoplist.data

import com.goodluckys.shoplist.domain.ShopListRepository
import com.goodluckys.shoplist.domain.shopitem
import java.lang.RuntimeException

object ShopListRepoImpl: ShopListRepository {   // наследовались от интерфейса(репозитория),для реализации функций

    private val shopList = mutableListOf<shopitem>() // Создали список,в котором будем хранить что-то
    private var autoID = 0

    override fun getShopList(): List<shopitem> {
       return shopList.toList()
    }

    override fun getItem(ItemId: Int): shopitem {
        return shopList.find { it.id == ItemId } ?: throw RuntimeException("Элемента с номером $ItemId не найдено :(")
    }

    override fun add(item: shopitem) {
        if(item.id == shopitem.UNDEF) {  // Почитать
            item.id = autoID++
        }
        shopList.add(item)
    }

    override fun delete(item: shopitem) { // удаление элемента из списка
        shopList.remove(item)
    }

    override fun edit(item: shopitem) {  // получаем на вход готовый новый элемент,чтобы изменить список - удаляем старый,втыкаем новый
        val oldElement = getItem(item.id)
        shopList.remove(oldElement)
        add(item)
    }
}