package com.example.mycontacs.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycontacs.R
import com.example.mycontacs.adapter.AdapterRecyclerView
import com.example.mycontacs.model.ModelContactsItem
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ContactList : Fragment(),AdapterRecyclerView.OnClickSelectedItem {

    private val contactsViewModel: ContactsViewModel by sharedViewModel()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private var adapterRecycler: AdapterRecyclerView =
        AdapterRecyclerView(arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactsViewModel.model.observe(this, Observer ( ::uiUpdate))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    fun uiUpdate(model: ContactsViewModel.UiModel){
        when(model){
            is ContactsViewModel.UiModel.Loading -> Log.i("Carpul", "Loading")
            is ContactsViewModel.UiModel.Content -> {
                adapterRecycler.addData(model.contacts)
        }
            is ContactsViewModel.UiModel.ShowUi -> {
                contactsViewModel.getContacts()
            }
        }
    }

    private fun setupRecyclerView() {
        linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerViewListContacts.apply {
            layoutManager = linearLayoutManager
            adapter = adapterRecycler
        }
    }

    override fun onClick(pos: Int) {
        contactsViewModel.pos = pos
        val navController = findNavController()
        navController.navigate(R.id.action_contactList_to_contactDetail)
    }

}