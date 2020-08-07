package com.example.mycontacs.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mycontacs.R
import com.example.mycontacs.adapter.AdapterListView
import com.example.mycontacs.adapter.ItemDetailModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ContactDetail : Fragment() {

    private val contactsViewModel: ContactsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView() {
        val contactDetail = contactsViewModel.getContactAtPosition()
        nameDetailContact.text = contactDetail.name
        descriptionDetailContact.text = contactDetail.companyName
        Picasso.with(requireContext()).load(contactDetail.largeImageURL)
            .placeholder(R.drawable.user_image_large)
            .resize(560, 560)
            .centerCrop()
            .into(imageViewDetailContact)

        val address = contactDetail.address.run {

                "${street}\n$city, $state $zipCode, $country"
           
        }.toString()

        val dataContactSelected = arrayListOf<ItemDetailModel?>()

        if (!contactDetail.phone.home.isNullOrEmpty())
            dataContactSelected.add(ItemDetailModel("PHONE", contactDetail.phone.home, "Home"))
        if (!contactDetail.phone.mobile.isNullOrEmpty())
            dataContactSelected.add(ItemDetailModel("PHONE", contactDetail.phone.mobile, "Mobile"))
        if (!contactDetail.phone.work.isNullOrEmpty())
            dataContactSelected.add(ItemDetailModel("PHONE", contactDetail.phone.work, "Work"))

            dataContactSelected.add(ItemDetailModel("ADDRESS", address, ""))
            dataContactSelected.add(ItemDetailModel("BIRTHDAY", contactDetail.birthdate, ""))
            dataContactSelected.add(ItemDetailModel("EMAIL", contactDetail.emailAddress, ""))

        val adapter = AdapterListView(activity, dataContactSelected)
        listViewDetails.adapter = adapter



    }
}