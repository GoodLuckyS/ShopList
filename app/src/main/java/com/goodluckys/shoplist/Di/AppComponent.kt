package com.goodluckys.shoplist.Di

import android.app.Application
import com.goodluckys.shoplist.Di.modules.AppModule
import com.goodluckys.shoplist.presentation.screens.EditFragment
import com.goodluckys.shoplist.presentation.screens.ListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(listFragment: ListFragment)
    fun inject(editFragment: EditFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application,
        ): AppComponent
    }

}