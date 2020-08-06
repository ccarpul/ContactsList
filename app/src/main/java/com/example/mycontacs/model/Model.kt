package com.example.mycontacs.model

class Model : ArrayList<Model.ModelContactsItem>(){
    data class ModelContactsItem(
        val address: Address,
        val birthdate: String,
        val companyName: String,
        val emailAddress: String,
        val id: String,
        val isFavorite: Boolean,
        val largeImageURL: String,
        val name: String,
        val phone: Phone,
        val smallImageURL: String
    ) {
        data class Address(
            val city: String,
            val country: String,
            val state: String,
            val street: String,
            val zipCode: String
        )
    
        data class Phone(
            val home: String,
            val mobile: String,
            val work: String
        )
    }
}