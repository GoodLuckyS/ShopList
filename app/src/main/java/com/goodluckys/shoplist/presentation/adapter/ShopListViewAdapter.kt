package com.goodluckys.shoplist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.goodluckys.shoplist.databinding.DisableItemBinding
import com.goodluckys.shoplist.databinding.EnableItemBinding
import com.goodluckys.shoplist.domain.ShopItem
import java.lang.RuntimeException

class ShopListViewAdapter :
    ListAdapter<ShopItem, ShopListViewAdapter.ShopListViewHolder>(ShopItemDiffCallback()) {

    var onLongClickListener: ((item: ShopItem) -> Unit)? = null
    var onClickListener: ((item: ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ENABLED_TYPE -> ShopListViewHolder.Enabled(
                EnableItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            DISABLED_TYPE -> ShopListViewHolder.Disabled(
                DisableItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw RuntimeException("Error here,type not found : $viewType")
        }
    }


    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        val item = getItem(position)
        holder.onLongClickListener = onLongClickListener
        holder.onClickListener = onClickListener
        when (holder) {
            is ShopListViewHolder.Enabled -> holder.bind(item)
            is ShopListViewHolder.Disabled -> holder.bind(item)

        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) {
            ENABLED_TYPE
        } else {
            DISABLED_TYPE
        }
    }

    sealed class ShopListViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        var onLongClickListener: ((item: ShopItem) -> Unit)? = null
        var onClickListener: ((item: ShopItem) -> Unit)? = null

        class Enabled(private val binding: EnableItemBinding) : ShopListViewHolder(binding) {
            fun bind(enable: ShopItem) {

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

        class Disabled(private val binding: DisableItemBinding) : ShopListViewHolder(binding) {
            fun bind(disable: ShopItem) {
                binding.tvItem.text = disable.name
                binding.tvCount.text = disable.count.toString()
                binding.root.setOnLongClickListener {
                    onLongClickListener?.invoke(disable)
                    true
                }
                binding.root.setOnClickListener {
                    onClickListener?.invoke(disable)
                    true
                }

            }
        }
    }


    companion object {
        const val ENABLED_TYPE = 1
        const val DISABLED_TYPE = 2
    }


}

















