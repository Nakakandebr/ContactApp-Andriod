package com.assignment.contatslist.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.contatslist.model.ContactData


@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact :ContactData)
    @Query("SELECT*FROM Contacts ORDER BY displayName")
    fun getAllContacts():LiveData<List<ContactData>>

    @Query("SELECT * FROM Contacts WHERE contactId = :contactId")
    fun getContactById(contactId: Int): LiveData<ContactData>

}