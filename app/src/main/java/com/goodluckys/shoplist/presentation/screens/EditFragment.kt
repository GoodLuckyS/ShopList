package com.goodluckys.shoplist.presentation.screens

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.goodluckys.shoplist.R
import com.goodluckys.shoplist.databinding.FragmentEditBinding
import com.goodluckys.shoplist.presentation.ViewModels.EditViewModel


class EditFragment : Fragment(R.layout.fragment_edit) {

    lateinit var binding: FragmentEditBinding

    private lateinit var viewModel: EditViewModel

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
        viewModel = ViewModelProvider(this)[EditViewModel::class.java]
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        binding.btBack.setOnClickListener {
            findNavController().popBackStack()
        }
        when (args.type) {
            edittypeKey -> launchEditMode()
            addtypeKey -> launchAddMode()
        }
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            binding.edtName.error = message
        }
        viewModel.errorInputCount.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_count)
            } else {
                null
            }
            binding.edtCount.error = message
        }
    }


    private fun launchAddMode() {
        binding.btSave.setOnClickListener {
            val itemName = binding.edName.text.toString()
            val itemCount = binding.edCount.text.toString()
            viewModel.addItem(itemName, itemCount)
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
            val itemName = binding.edName.text.toString()
            val itemCount = binding.edCount.text.toString()
            viewModel.editItem(itemName, itemCount)
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