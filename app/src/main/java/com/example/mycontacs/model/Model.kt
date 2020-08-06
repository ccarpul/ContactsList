package com.example.mycontacs.model

import android.location.Address
import android.provider.ContactsContract

data class ModelItem(
    val address: Address,
    val birthDate: String,
    val companyName: String,
    val emailAddress: String,
    val id: String,
    val isFavorite: Boolean,
    val largeImageURL: String,
    val name: String,
    val phone: ContactsContract.CommonDataKinds.Phone,
    val smallImageURL: String)

data class Address(

    val city: String,
    val country: String,
    val state: String,
    val street: String,
    val zipCode: String)

data class Phone(
    val home: String,
    val mobile: String,
    val work: String
)