package com.goodluckys.shoplist.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.synchronized

@Database(
    version = 1,
    entities = [
        ShopItemDb::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getShopItemsDao(): ShopListDao

    companion object {

        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ShopItems"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}