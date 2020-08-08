package com.example.mycontacs.model


data class Model(val modelContactsItem: ModelContactsItem): ArrayList<ModelContactsItem>()

data class ModelContactsItem(
    val name: String,
    val id: String,
    val companyName: String,
    val isFavorite: Boolean,
    val smallImageURL: String,
    val largeImageURL: String,
    val emailAddress: String,
    val birthdate: String,
    val phone: Phone,
    val address: Address
)

data class Address(
    val city: String,
    val country: String,
    val state: String,
    val street: String,
    val zipCode: String
)
data class Phone(
    val home: String?,
    val mobile: String?,
    val work: String?
)