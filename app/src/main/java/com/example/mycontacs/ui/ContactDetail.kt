package com.example.mycontacs.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mycontacs.MainActivity
import com.example.mycontacs.R
import com.example.mycontacs.adapter.AdapterListView
import com.example.mycontacs.adapter.ItemDetailModel
import com.example.mycontacs.data.model.ModelContactsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ContactDetail : Fragment() {

    private val contactsViewModel: ContactsViewModel by sharedViewModel()
    private lateinit var textViewTitleBar: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewTitleBar = (activity as MainActivity).textView_titleBar
        textViewTitleBar.text = getString(R.string.titleToolbar)
        textViewTitleBar.setTextColor(Color.BLUE)
        textViewTitleBar.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
        textViewTitleBar.setOnClickListener {
            findNavController().apply {
                navigate(R.id.contactList)
            }
        }
        initView()
    }

    fun initView() {
        val contactDetail = contactsViewModel.getContactAtPosition() as ModelContactsItem
        nameDetailContact.text = contactDetail.name
        descriptionDetailContact.text = contactDetail.companyName
        Picasso.with(requireContext()).load(contactDetail.largeImageURL)
            .placeholder(R.drawable.user_image_large)
            .resize(590, 590)
            .centerCrop()
            .into(imageViewDetailContact)

        val address = contactDetail.address.run {
            "${street}\n$city, $state $zipCode, $country"
        }.toString()

        val dataContactSelected = arrayListOf<ItemDetailModel?>()

        if (!contactDetail.phone.home.isNullOrEmpty())
            dataContactSelected.add(
                ItemDetailModel(
                    getString(R.string.phoneitemtitle),
                    contactDetail.phone.home, getString(R.string.homeTypePhone)
                )
            )
        if (!contactDetail.phone.mobile.isNullOrEmpty())
            dataContactSelected.add(
                ItemDetailModel(
                    getString(R.string.phoneitemtitle),
                    contactDetail.phone.mobile, getString(R.string.mobileTypePhone)
                )
            )
        if (!contactDetail.phone.work.isNullOrEmpty())
            dataContactSelected.add(
                ItemDetailModel(
                    getString(R.string.phoneitemtitle),
                    contactDetail.phone.work, getString(R.string.workTypePhone)
                )
            )

        dataContactSelected.add(
            ItemDetailModel(
                getString(R.string.addressItemTitle), address, ""
            )
        )
        dataContactSelected.add(
            ItemDetailModel(
                getString(R.string.birthdayItemTitle), contactDetail.birthdate, ""
            )
        )
        dataContactSelected.add(
            ItemDetailModel(
                getString(
                    R.string.emailItemTitle
                ), contactDetail.emailAddress, ""
            )
        )

        if (contactDetail.isFavorite) {
            textViewTitleBar.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_back_arrow, 0,
                R.drawable.image_favorite_true, 0
            )
        } else {
            textViewTitleBar.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_back_arrow, 0,
                R.drawable.image_favorite_false, 0
            )
        }


        val adapter = AdapterListView(activity, dataContactSelected)
        listViewDetails.adapter = adapter
    }

}