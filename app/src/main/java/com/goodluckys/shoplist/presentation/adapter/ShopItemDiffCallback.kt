package com.goodluckys.shoplist.presentation.adapter

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.goodluckys.shoplist.domain.shopitem

class ShopItemDiffCallback(
    val oldList: List<shopitem>,
    val newList: List<shopitem>
) : DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        Log.d("Jopa","This is DU $oldItem ---------- $newItem")
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}