package com.goodluckys.shoplist.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goodluckys.shoplist.R
import com.goodluckys.shoplist.databinding.FragmentListBinding
import com.goodluckys.shoplist.presentation.ViewModels.MainViewModel
import com.goodluckys.shoplist.presentation.adapter.ShopListViewAdapter

class ListFragment : Fragment(R.layout.fragment_list) {
    lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentListBinding
    lateinit var shopListAdapter: ShopListViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRcView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.shopList.observe(viewLifecycleOwner) {
            shopListAdapter.submitList(it)
        }

        binding.addNewItem.setOnClickListener {
            openAdd()
        }

    }

    private fun setRcView() {
        val layoutManager = LinearLayoutManager(context) // context
        shopListAdapter = ShopListViewAdapter()
        with(binding) {
            shopListRC.adapter = shopListAdapter
            shopListRC.layoutManager = layoutManager
        }
        shopListAdapter.onLongClickListener = {
            viewModel.changeEnableState(it)
        }

        shopListAdapter.onClickListener = {
            openEdit(it.id)
        }
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteItemList(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.shopListRC)

    }

    private fun openEdit(id: Int) {
        val direction =
            ListFragmentDirections.actionListFragmentToEditFragment(EditFragment.edittypeKey, id)
        findNavController().navigate(
            direction,
        )

    }

    private fun openAdd() {
        val directions = ListFragmentDirections.actionListFragmentToEditFragment(
            EditFragment.addtypeKey,
            EditFragment.UNDEF_ID
        )
        findNavController().navigate(
            directions
        )
    }


}