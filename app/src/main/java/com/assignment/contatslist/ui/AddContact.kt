package com.assignment.contatslist.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Location
import android.location.LocationRequest
import android.media.MediaDataSource
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Looper
import android.provider.MediaStore
import android.provider.Telephony.Sms.Intents
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import com.assignment.contatslist.R
import com.assignment.contatslist.databinding.ActivityAddcpontactBinding
import com.assignment.contatslist.model.ContactData
import com.assignment.contatslist.viewmodel.ContactsViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.io.File

class AddContact : AppCompatActivity() {
    lateinit var binding: ActivityAddcpontactBinding
    val contactsViewModel: ContactsViewModel by viewModels()
    lateinit var fusedLocationClient : FusedLocationProviderClient
    lateinit var mylocation :Location

    lateinit var photoFile: File

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
//            val photo = BitmapFactory.decodeFile(photoFile.absolutePath)
//            binding.imageView.setImageBitmap(photo)
                Picasso
                    .get()
                    .load(
                        File(photoFile.absolutePath)
                    )
                    .resize(120, 120)
                    .centerCrop()
                    .transform(CropCircleTransformation())
                    .into(binding.imageView)
            }
        }
    val locationRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            }
            else{
//                Toast.makeText(this,"")
            }



        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddcpontactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onResume() {
        super.onResume()
        setContentView(binding.root)
        binding.button.setOnClickListener {
            validateform()
        }
        binding.imageView.setOnClickListener {
            capturePhoto()

        }
         getLocationUpdates()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun getLocationUpdates() {
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val locationRequest = LocationRequest.Builder(10000)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .build()
            val locationCallBack = object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    for (location in p0.locations) {
                        Toast.makeText(
                            baseContext, "lat:${location.latitude},Long:${location.longitude}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallBack,
                Looper.getMainLooper()
            )

        }
        else{
            locationRequest.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }

    private fun capturePhoto(){
        val photoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = getPhotoFile("photo_${System.currentTimeMillis()}")
        val fileUri = FileProvider.getUriForFile(this,"com.assignment.contatslist.provider" ,photoFile)
        photoIntent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri)
        cameraLauncher.launch(photoIntent)


    }
    private fun getPhotoFile(fileName :String):File{
        val folder= getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var tempFile = File.createTempFile(fileName , "jpeg" , folder)
        return tempFile
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
            val newContact = ContactData(contactId = 0, avatar =photoFile.absolutePath, displayName = name , phoneNumber = phoneNumber, email = email)
            contactsViewModel.saveContact(newContact)
            Toast.makeText(this,"contact saved", Toast.LENGTH_LONG).show()
            finish()
        }

    }
}







