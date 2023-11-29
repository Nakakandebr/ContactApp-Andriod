package com.assignment.contatslist.ui

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.assignment.contatslist.R
import com.assignment.contatslist.databinding.AddListItemBinding
import com.assignment.contatslist.model.ContactData
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.io.File

class ContactsAdapter( val contactList: List<ContactData>, val context: Context) : RecyclerView.Adapter<ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = AddListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = contactList[position]
        val binding = holder.binding

        binding.tvName.text = currentContact.displayName
        binding.tvPhoneNumber.text = currentContact.phoneNumber
        binding.tvEmailAddress.text = currentContact.email

        if (currentContact.avatar.isNotBlank()) {
            Picasso
                .get()
                .load(
                    File(currentContact.avatar))
                .resize(80, 80)
                .centerCrop()
                .transform(CropCircleTransformation())
                .into(binding.imageView)
        }

        binding.cvContact.setOnClickListener{
            val intent = Intent(context, ContactDetailsActivity::class.java)
            intent.putExtra("CONTACT_ID", currentContact.contactId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}

class ContactViewHolder(var binding: AddListItemBinding) : RecyclerView.ViewHolder(binding.root)


