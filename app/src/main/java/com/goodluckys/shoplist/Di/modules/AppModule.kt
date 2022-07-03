package com.goodluckys.shoplist.Di.modules

import com.goodluckys.shoplist.Di.modules.DataModule
import com.goodluckys.shoplist.Di.modules.ViewModelModule
import dagger.Module
import javax.inject.Singleton

@Module(includes = [
    ViewModelModule::class,
    DataModule::class
])
class AppModule {
}