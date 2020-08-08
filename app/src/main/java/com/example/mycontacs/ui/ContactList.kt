package com.example.mycontacs.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycontacs.MainActivity
import com.example.mycontacs.R
import com.example.mycontacs.adapter.AdapterRecyclerView
import com.example.mycontacs.adapter.Header
import com.example.mycontacs.data.model.Address
import com.example.mycontacs.data.model.ModelContactsItem
import com.example.mycontacs.data.model.Phone
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ContactList : Fragment(),AdapterRecyclerView.OnClickSelectedItem {

    private val contactsViewModel: ContactsViewModel by sharedViewModel()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private var adapterRecycler: AdapterRecyclerView =
        AdapterRecyclerView(arrayListOf(), this)

    private lateinit var textViewTitleBar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textViewTitleBar = (activity as MainActivity).textView_titleBar
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

    override fun onResume() {
        super.onResume()
        textViewTitleBar.text = getString(R.string.titleToolbar)
        textViewTitleBar.setTextColor(Color.WHITE)
        textViewTitleBar.textAlignment = View.TEXT_ALIGNMENT_CENTER
        textViewTitleBar.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0)
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