package com.goodluckys.shoplist.presentation

import android.app.Application
import android.content.Context
import com.goodluckys.shoplist.Di.AppComponent
import com.goodluckys.shoplist.Di.DaggerAppComponent

class MainApplication : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}
