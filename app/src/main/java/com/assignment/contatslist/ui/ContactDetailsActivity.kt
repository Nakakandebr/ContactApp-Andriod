package com.assignment.contatslist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.assignment.contatslist.databinding.ActivityContactDetailsBinding
import com.assignment.contatslist.model.ContactData
import com.assignment.contatslist.viewmodel.ContactsViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation


class ContactDetailsActivity : AppCompatActivity() {
    var contactId = 0
    private lateinit var viewModel:ContactsViewModel

    lateinit var binding: ActivityContactDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDelete.setOnClickListener {
        }




        viewModel = ContactsViewModel()
        val contactId = intent.getIntExtra("CONTACT_ID", 0)
        viewModel.getContactById(contactId).observe(this, Observer{ contact ->
            if (contact != null) {
                displayContactDetails(contact)
            } else {
                Toast.makeText(this, "Contact not found", Toast.LENGTH_SHORT).show()
            }
        })

    }
    private fun displayContactDetails(contact: ContactData) {
        binding.tvName.text = contact.displayName
        binding.tvPhoneNumber.text = contact.phoneNumber
        binding.tvEmail.text = contact.email
        if (!contact.avatar.isNullOrEmpty()) {
            Picasso
                .get()
                .load(contact.contactId)
                .resize(80, 80)
                .centerCrop()
                .transform(CropCircleTransformation())
                .into(binding.imageView2)
        }

    }
    }