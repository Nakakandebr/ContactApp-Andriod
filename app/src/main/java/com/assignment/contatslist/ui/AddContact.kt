package com.assignment.contatslist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.assignment.contatslist.R
import com.assignment.contatslist.databinding.ActivityAddcpontactBinding
import com.assignment.contatslist.model.ContactData
import com.assignment.contatslist.viewmodel.ContactsViewModel

class AddContact : AppCompatActivity() {
    lateinit var binding: ActivityAddcpontactBinding

    val contactsViewModel: ContactsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddcpontactBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setContentView(binding.root)
        binding.button.setOnClickListener {
            validateform()
        }

    }

    fun validateform() {
        val name = binding.etNAME.text.toString()
        val email = binding.etemail.text.toString()
        val phoneNumber = binding.etPhonenumber.text.toString()

        var error = false

        if (email.isBlank()) {
            binding.tilemail.error = getString(R.string.email_is_required)
            error = true

        }

        if (name.isBlank()) {
            binding.tilname.error =(getString(R.string.name_required))
            error = true

        }
        if (phoneNumber.isBlank()) {
            binding.tilPhonenumber.error =getString(R.string.phone_number_required)
            error = true

        }
        if(!error){
            val newContact = ContactData(contactId = 0, avatar = "" , displayName = name , phoneNumber = phoneNumber, email = email)
            contactsViewModel.saveContact(newContact)
            Toast.makeText(this,"contact saved", Toast.LENGTH_LONG).show()
            finish()
        }

    }
}






