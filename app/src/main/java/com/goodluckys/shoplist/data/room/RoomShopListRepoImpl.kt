package com.goodluckys.shoplist.data.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.goodluckys.shoplist.domain.ShopItem
import com.goodluckys.shoplist.domain.ShopListRepository
import javax.inject.Inject


class RoomShopListRepoImpl @Inject constructor(
    private val shopListDao: ShopListDao,
    private val mapper: ShopItemMapper,
) : ShopListRepository {

    override fun getShopList(): LiveData<List<ShopItem>> =
        MediatorLiveData<List<ShopItem>>().apply {
            addSource(shopListDao.getList()) {
                value = mapper.mapListDbToListEntity(it)
            }
        }

    override suspend fun getItem(ItemId: Int): ShopItem {
        val item = shopListDao.getItem(ItemId)
        return mapper.mapToItem(item)
    }

    override suspend fun add(item: ShopItem) {
        val itemDB = mapper.mapToDbModel(item)
        shopListDao.addItem(itemDB)
    }

    override suspend fun delete(item: ShopItem) {
        val itemDB = mapper.mapToDbModel(item)
        shopListDao.deleteItem(itemDB)
    }

    override suspend fun edit(item: ShopItem) {
        val itemDB = mapper.mapToDbModel(item)
        shopListDao.editItem(itemDB)
    }

}
