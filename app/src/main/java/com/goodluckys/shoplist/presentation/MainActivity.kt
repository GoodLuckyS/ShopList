package com.goodluckys.shoplist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.goodluckys.shoplist.R
import com.goodluckys.shoplist.databinding.ActivityMainBinding
import com.goodluckys.shoplist.presentation.ViewModels.MainViewModel
import com.goodluckys.shoplist.presentation.adapter.ShopListViewAdapter
import com.goodluckys.shoplist.presentation.screens.EditFragment
import com.goodluckys.shoplist.presentation.screens.ListFragment

class MainActivity :
    AppCompatActivity() {


    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}




