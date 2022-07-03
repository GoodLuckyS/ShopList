package com.goodluckys.shoplist.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.goodluckys.shoplist.data.room.ShopItemDb


@Dao
interface ShopListDao {

    @Query("SELECT * FROM ShopItems")
    fun getList(): LiveData<List<ShopItemDb>>

    @Query("SELECT * FROM ShopItems WHERE id=:id")
    suspend fun getItem(id: Int): ShopItemDb

    @Update
    suspend fun editItem(item: ShopItemDb)

    @Insert
    suspend fun addItem(item: ShopItemDb)

    @Delete
    suspend fun deleteItem(item: ShopItemDb)

}