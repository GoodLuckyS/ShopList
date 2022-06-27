//package com.goodluckys.shoplist.data
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import com.goodluckys.shoplist.domain.ShopListRepository
//import com.goodluckys.shoplist.domain.ShopItem
//import java.lang.RuntimeException
//import kotlin.random.Random
//
//object ShopListRepoImpl:
//    ShopListRepository {   // наследовались от интерфейса(репозитория),для реализации функций,через DI class
//
//    private var shopList =mutableListOf<ShopItem>() // Создали список,в котором будем хранить что-то
//    private var autoID = 0
//    private val shopListLD = MutableLiveData<List<ShopItem>>()  // добавить сортировку три сет
//
//    init {
//        for (i in 0 until 21) {
//            val soap = ShopItem(
//                i,
//                "Name is $i",
//                120,
//                Random.nextBoolean()
//            )
//            add(soap)
//        }
//    }
//
//    override suspend fun getShopList(): LiveData<List<ShopItem>> {
//        return shopListLD
//    }
//
//    override suspend fun getItem(ItemId: Int): ShopItem {
//        return shopList.find { it.id == ItemId }
//            ?: throw RuntimeException("Элемента с номером $ItemId не найдено :(") // TODO()
//    }
//
//    override suspend fun add(item: ShopItem) {
////        if (item.id == ShopItem.UNDEF) {  // class UUID
////            item.id = autoID++
////        }
//        shopList.add(item)
//        updateList()
//    }
//
//    override suspend fun delete(item: ShopItem) {
//        shopList.remove(item)
//        updateList()
//    }
//
//    override suspend fun edit(item: ShopItem) {  // получаем на вход готовый новый элемент,чтобы изменить список - удаляем старый,втыкаем новый
//        val oldElement = getItem(item.id)
//        shopList.remove(oldElement)
//        add(item)
//    }
//
//    private fun updateList() {
//        shopListLD.value = shopList.toList()
//        shopListLD.value = shopListLD.value
//    }
//}