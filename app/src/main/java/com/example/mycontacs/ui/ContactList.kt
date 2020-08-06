package com.example.mycontacs.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.mycontacs.R
import org.koin.android.viewmodel.ext.android.viewModel

class ContactList : Fragment() {

    private val contactsViewModel: ContactsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contactsViewModel.model.observe(this, Observer ( ::uiUpdate))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.contact_list, container, false)
    }

    fun uiUpdate(model: ContactsViewModel.UiModel){
        when(model){
            is ContactsViewModel.UiModel.Loading -> Log.i("Carpul", "Loading")
            is ContactsViewModel.UiModel.Content -> Log.i("Carpul", "${model.contacts}")
            is ContactsViewModel.UiModel.ShowUi -> {
                Log.i("Carpul", "getContacts")
                contactsViewModel.getContacts()
            }
        }
    }

}