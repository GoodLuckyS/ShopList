package com.goodluckys.shoplist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.goodluckys.shoplist.databinding.DisableItemBinding
import com.goodluckys.shoplist.databinding.EnableItemBinding
import com.goodluckys.shoplist.domain.shopitem
import com.goodluckys.shoplist.presentation.ShopListViewAdapter.ShopListViewHolder.Enabled
import java.lang.RuntimeException

class ShopListViewAdapter: RecyclerView.Adapter<ShopListViewAdapter.ShopListViewHolder>() {


    var shopList = listOf<shopitem>()
    set(value) {
        field=value
        notifyDataSetChanged()
    }

    var onLongClickListener : ((item:shopitem) -> Unit)? = null
    var onClickListener : ((item:shopitem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
//        Log.d("RCA","OnCreate - ${++count}")
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            ENABLED_TYPE-> ShopListViewHolder.Enabled(
                EnableItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            DISABLED_TYPE->ShopListViewHolder.Disabled(
                DisableItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            ) else-> throw RuntimeException("Error here,type not found : $viewType")
        }
        }


    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        val item = shopList[position]
        holder.onLongClickListener=onLongClickListener
        holder.onClickListener=onClickListener
        when(holder){
            is ShopListViewHolder.Enabled  -> holder.bind(item)
            is ShopListViewHolder.Disabled -> holder.bind(item)

        }
        }



    override fun getItemViewType(position: Int): Int {
        return if (shopList[position].Enabled) {
            ENABLED_TYPE
        } else {
            DISABLED_TYPE
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

  sealed  class ShopListViewHolder(binding:ViewBinding):RecyclerView.ViewHolder(binding.root){

      var onLongClickListener : ((item:shopitem) -> Unit)? = null
      var onClickListener : ((item:shopitem) -> Unit)? = null

      class Enabled(private val binding: EnableItemBinding) : ShopListViewHolder(binding){
          fun bind(enable: shopitem){

             binding.tvItem.text = enable.name
             binding.tvCount.text = enable.count.toString()
             binding.root.setOnLongClickListener {
                 onLongClickListener?.invoke(enable)
                 true
             }
              binding.root.setOnClickListener {
                  onClickListener?.invoke(enable)
                  true
              }
          }
      }
      class Disabled(private val binding: DisableItemBinding):ShopListViewHolder(binding){
          fun bind(disable: shopitem){
              binding.tvItem.text = disable.name
              binding.tvCount.text = disable.count.toString()
              binding.root.setOnLongClickListener {
                  onLongClickListener?.invoke(disable)
                  true
              }
              binding.root.setOnClickListener {
                  onClickListener?.invoke(disable)
              }

          }
      }
    }

    companion object{
        const val ENABLED_TYPE = 1
        const val DISABLED_TYPE = 2

        const val MAX_POOL_SIZE = 8 // добавить в код

    }


}

















