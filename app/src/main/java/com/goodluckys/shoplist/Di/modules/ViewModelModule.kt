package com.goodluckys.shoplist.Di.modules

import androidx.lifecycle.ViewModel
import com.goodluckys.shoplist.Di.EditViewModelKey
import com.goodluckys.shoplist.Di.ListViewModel
import com.goodluckys.shoplist.presentation.ViewModels.MainViewModel
import dagger.Binds
import dagger.Module
import com.goodluckys.shoplist.presentation.ViewModels.EditViewModel
import javax.inject.Singleton

@Module
interface ViewModelModule {
    @Singleton
    @Binds
    @ListViewModel
    fun bindsMainViewModel(viewModel: MainViewModel) : ViewModel


    @Singleton
    @Binds
    @EditViewModelKey
    fun bindsEditViewModel(viewModel: EditViewModel) : ViewModel

}


