package com.assignment.contatslist.repository

import androidx.lifecycle.LiveData
import com.assignment.contatslist.MyContactsApp
import com.assignment.contatslist.database.ContactDb
import com.assignment.contatslist.model.ContactData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepository {
    val database = ContactDb.getDataBase(MyContactsApp.appContext)

    suspend fun saveContact(contact:ContactData){
        withContext(Dispatchers.IO){
            database.getContactDao().insertContact(contact)
        }
    }

    fun getAllContacts():LiveData<List<ContactData>>{
        return database.getContactDao().getAllContacts()
    }


    fun getContactById(contactId: Int): LiveData<ContactData> {
        return database.getContactDao().getContactById(contactId)
    }
}