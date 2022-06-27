package com.goodluckys.shoplist.data.room

import com.goodluckys.shoplist.data.room.ShopItemDb
import com.goodluckys.shoplist.domain.ShopItem

class ShopItemMapper {
    fun mapToDbModel(item:ShopItem) : ShopItemDb {
            return ShopItemDb(id = item.id,
            name=item.name,
            count=item.count,
            enabled = item.enabled)
    }

    fun mapToItem(item: ShopItemDb):ShopItem {
        return ShopItem(name = item.name,
        count = item.count,
        enabled = item.enabled,
        id = item.id)
    }

    fun mapListDbToListEntity(listDb: List<ShopItemDb>) = listDb.map {
        mapToItem(it)
    }


}