package com.example.dataentry

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel(
    private val repo:Repo
):ViewModel() {
    val listOfContactLiveData=MutableLiveData<List<contact_data>>()

    init {
        getAllData()
    }
    fun getAllData(){
        val allContact=repo.getAllContact()
        listOfContactLiveData.value=allContact
    }

    fun addContact(contactData: contact_data){
        repo.AddContact(contactData)
    }
}