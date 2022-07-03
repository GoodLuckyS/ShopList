package com.goodluckys.shoplist.Di.modules

import android.app.Application
import com.goodluckys.shoplist.data.room.AppDatabase
import com.goodluckys.shoplist.data.room.RoomShopListRepoImpl
import com.goodluckys.shoplist.data.room.ShopListDao
import com.goodluckys.shoplist.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module

interface DataModule {
    @Singleton
    @Binds
    fun bindShopListRepository(impl:RoomShopListRepoImpl):ShopListRepository

    companion object {
        @Singleton
        @Provides
        fun provideShopListDao(application: Application) : ShopListDao {
            return AppDatabase.getDatabase(application).getShopItemsDao()
        }
    }

}