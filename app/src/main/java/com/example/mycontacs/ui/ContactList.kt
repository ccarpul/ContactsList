package com.example.mycontacs.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mycontacs.R
import org.koin.android.viewmodel.ext.android.viewModel

class ContactList : Fragment() {

    private val contactsViewModel: ContactsViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.contact_list, container, false)
        
    }

}