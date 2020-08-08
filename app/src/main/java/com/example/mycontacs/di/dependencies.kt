package com.example.mycontacs.di

import com.example.mycontacs.data.api.getApiService
import com.example.mycontacs.ui.ContactsRepository
import com.example.mycontacs.ui.ContactsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dependencyModule =  module {
    viewModel { ContactsViewModel(get()) }
    single { ContactsRepository(get()) }
    single { getApiService() }
}