package com.assignment.contatslist.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.contatslist.model.ContactData
import com.assignment.contatslist.databinding.ActivityMainBinding
import com.assignment.contatslist.viewmodel.ContactsViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    val contactsViewModel :ContactsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        contactsViewModel.getContacts().observe(this, Observer { contactList->diplayContacts(contactList) })

        binding.button2.setOnClickListener {
            val intent = Intent(this, AddContact::class.java)
            startActivity(intent)
        }
    }


    fun diplayContacts(contactList:List<ContactData>){
        val contacts= ContactsAdapter(contactList,this)
        binding.rvcontacts.layoutManager=LinearLayoutManager(this)
        binding.rvcontacts.adapter=contacts

    }

}