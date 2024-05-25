package com.example.dataentry

class Repo {
    private val listOfContact= mutableListOf<contact_data>()
    fun getAllContact()=listOfContact
    fun AddContact(contact:contact_data)=listOfContact.add(contact)
}