package com.assignment.contatslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.contatslist.model.ContactData
import com.assignment.contatslist.repository.ContactsRepository
import kotlinx.coroutines.launch

class ContactsViewModel:ViewModel() {
    val contactsRepo = ContactsRepository()
            fun saveContact(contact:ContactData){
                viewModelScope.launch {
                    contactsRepo.saveContact(contact)
                }
            }
fun getContacts():LiveData<List<ContactData>>{
    return contactsRepo.getAllContacts()
}
    fun getContactById(contactId:Int):LiveData<ContactData>{
        return  contactsRepo.getContactById(contactId)
    }
}