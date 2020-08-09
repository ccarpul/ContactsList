package com.example.mycontacs.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mycontacs.MainActivity
import com.example.mycontacs.R
import com.example.mycontacs.adapter.AdapterListView
import com.example.mycontacs.adapter.modelAdapter.ItemDetailModel
import com.example.mycontacs.data.model.ModelContactsItem
import com.example.mycontacs.utils.setIconCategoryContact
import com.example.mycontacs.utils.setImage
import com.example.mycontacs.utils.setupToolbar
import com.example.mycontacs.utils.show
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ContactDetail : Fragment() {

    private val contactsViewModel: ContactsViewModel by sharedViewModel() //Injection Dependency

    private lateinit var textViewTitleBar: TextView
    private lateinit var imageViewTitleBar: AppCompatImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contactDetail = contactsViewModel.getContactAtPosition() as ModelContactsItem
        configToolbar(contactDetail)
        setupItemsDetail(contactDetail)

        val callback =
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                contactsViewModel.setCategory(contactDetail.isFavorite, contactDetail.id)
                findNavController().navigate(R.id.action_contactDetail_to_contactList)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun setupItemsDetail(contactDetail: ModelContactsItem) {

        nameDetailContact.text = contactDetail.name
        descriptionDetailContact.text = contactDetail.companyName

        Picasso.with(requireContext()).load(contactDetail.largeImageURL)
            .placeholder(R.drawable.user_image_large)
            .resize(590, 590)
            .centerCrop()
            .into(imageViewDetailContact)

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
        val address = contactDetail.address.run {
            "${street}\n$city, $state $zipCode, $country"
        }.toString()

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
        imageViewTitleBar.show()
        imageViewTitleBar.setIconCategoryContact(contactDetail.isFavorite)
        textViewTitleBar.setImage(R.drawable.ic_back_arrow)

        listViewDetails.adapter = AdapterListView(activity, dataContactSelected)
    }

    private fun configToolbar(contactDetail: ModelContactsItem) {
        textViewTitleBar = (activity as MainActivity).textView_titleBar
        textViewTitleBar.setupToolbar(
            getString(R.string.titleToolbar),
            resources.getColor(R.color.colorAccent),
            View.TEXT_ALIGNMENT_TEXT_START
        )
        textViewTitleBar.setOnClickListener {

            findNavController().navigate(R.id.action_contactDetail_to_contactList)
            contactsViewModel.setCategory(contactDetail.isFavorite, contactDetail.id)
        }

        imageViewTitleBar = (activity as MainActivity).toolBarImageCategory
        imageViewTitleBar.setOnClickListener {
            imageViewTitleBar.setIconCategoryContact(!contactDetail.isFavorite)
            contactDetail.isFavorite = !contactDetail.isFavorite
        }
    }
}