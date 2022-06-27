package com.goodluckys.shoplist.data.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.goodluckys.shoplist.domain.ShopItem
import com.goodluckys.shoplist.domain.ShopListRepository


class RoomShopListRepoImpl(
    application: Application
) : ShopListRepository {

    private val shopListDao = AppDatabase.getDatabase(application).getShopItemsDao()

    override fun getShopList(): LiveData<List<ShopItem>> = MediatorLiveData<List<ShopItem>>().apply {
        addSource(shopListDao.getList()){
        value = ShopItemMapper().mapListDbToListEntity(it)
        }
    }

    override suspend fun getItem(ItemId: Int): ShopItem {
        val item = shopListDao.getItem(ItemId)
        return ShopItemMapper().mapToItem(item)
    }

    override suspend fun add(item: ShopItem) {
        val itemDB = ShopItemMapper().mapToDbModel(item)
        shopListDao.addItem(itemDB)
    }

    override suspend fun delete(item: ShopItem) {
        val itemDB = ShopItemMapper().mapToDbModel(item)
        shopListDao.deleteItem(itemDB)
    }

    override suspend fun edit(item: ShopItem) {
        val itemDB = ShopItemMapper().mapToDbModel(item)
        shopListDao.editItem(itemDB)
    }

}
