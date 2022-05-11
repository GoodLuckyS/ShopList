package com.goodluckys.shoplist.domain

data class shopitem(val name:String,val count:Int,val Enabled:Boolean,var id:Int=UNDEF) {


    companion object {
        const val UNDEF = -1
    }
}
