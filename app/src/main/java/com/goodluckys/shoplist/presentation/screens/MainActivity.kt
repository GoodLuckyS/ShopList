package com.goodluckys.shoplist.presentation.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodluckys.shoplist.databinding.ActivityMainBinding
import com.goodluckys.shoplist.presentation.MainViewModel
import com.goodluckys.shoplist.presentation.ShopListViewAdapter

class MainActivity : AppCompatActivity() {


    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var adapter : ShopListViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRcView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(this){
            println(it)
            adapter.shopList = it
        }
    }

    private fun setRcView(){
        val layoutManager = LinearLayoutManager(this)
        adapter = ShopListViewAdapter()
        binding.shopListRC.adapter = adapter
        binding.shopListRC.layoutManager = layoutManager
    }
}