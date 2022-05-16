package com.goodluckys.shoplist.presentation.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodluckys.shoplist.databinding.ActivityMainBinding
import com.goodluckys.shoplist.domain.shopitem
import com.goodluckys.shoplist.presentation.MainViewModel
import com.goodluckys.shoplist.presentation.ShopListViewAdapter

class MainActivity : AppCompatActivity() {


    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var shopListAdapter : ShopListViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRcView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(this){
            println(it)
            shopListAdapter.shopList = it
        }
    }

    private fun setRcView(){
        val layoutManager = LinearLayoutManager(this)
        shopListAdapter = ShopListViewAdapter()
        with(binding){
        shopListRC.adapter = shopListAdapter
        shopListRC.layoutManager = layoutManager
            shopListRC.recycledViewPool.setMaxRecycledViews(
                ShopListViewAdapter.ENABLED_TYPE,
                 ShopListViewAdapter.MAX_POOL_SIZE
            )
            shopListRC.recycledViewPool.setMaxRecycledViews(
                ShopListViewAdapter.DISABLED_TYPE,
                ShopListViewAdapter.MAX_POOL_SIZE
            )
        }
     shopListAdapter.onLongClickListener = {
         viewModel.changeEnableState(it)
     }

        shopListAdapter.onClickListener = {
//            println(it.toString())
        }


    }


}