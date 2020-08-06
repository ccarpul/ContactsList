package com.example.mycontacs.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class ContactsViewModel(private val contactsRespository: ContactsRepository):
    ViewModel(), CoroutineScope {


    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    init { job = SupervisorJob() }

    override fun onCleared() { 
        job.cancel()
        super.onCleared()
    }
}