package com.goodluckys.shoplist.Di

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ListViewModel

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class EditViewModelKey