package com.goodluckys.shoplist.presentation.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.goodluckys.shoplist.R
import com.goodluckys.shoplist.databinding.FragmentEditBinding
import com.goodluckys.shoplist.presentation.ViewModels.EditViewModel


class EditFragment : Fragment(R.layout.fragment_edit) {

    lateinit var binding: FragmentEditBinding

    lateinit var viewModel: EditViewModel

    private val args: EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)

        when (args.type) {
            edittypeKey -> launchEditMode()
            addtypeKey -> launchAddMode()
        }

        viewModel.rFinish.observe(viewLifecycleOwner){
            findNavController().popBackStack()
        }

    }

    private fun launchAddMode() {
        binding.btSave.setOnClickListener {
            viewModel.addItem("sukaaaa", "10")
        }
    }

    private fun launchEditMode() {
        viewModel.getItem(args.id)
        viewModel.itemLD.observe(viewLifecycleOwner) {
            binding.apply {
                edName.setText(it.name)
                edCount.setText(it.count.toString())
            }
        }
        binding.btSave.setOnClickListener {
            viewModel.editItem("sukaaaa", "10")
        }


    }

    companion object {
        // keys
        const val edittypeKey = "EDIT_TYPE_KEY"
        const val addtypeKey = "ADD_TYPE_KEY"

        //
        const val UNDEF_ID = -1
    }
}