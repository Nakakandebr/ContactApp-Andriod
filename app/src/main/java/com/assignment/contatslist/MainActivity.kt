package com.assignment.contatslist


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.contatslist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        diplayContacts()
    }
    fun diplayContacts(){
        val contact1=ContactData("","Milcah","0720987542","milcah2@gmail.com")
        val contact2=ContactData("","Rachel","0735426780","racheal23@gmail.com")
        val contact3=ContactData("","Bridget","0742426767","bridget4@gmail.com")
        val contact4=ContactData("","Rebecca","0722426780","rebecca30@gmail.com")
        val contact5=ContactData("","Mary","0720987542","mary2@gmail.com")
        val contact6=ContactData("","Amina","0735426780","aminaraji23@gmail.com")
        val contact7=ContactData("","Kareem","0742426767","kareem4@gmail.com")
        val contact8=ContactData("","Naj","0722426780","naraji30@gmail.com")
        val contact9=ContactData("","Kelitu","0720987542","kelitu2@gmail.com")
        val contact10=ContactData("","Amanda","0735426780","amadnaraji23@gmail.com")
        val contact11=ContactData("","Kakande","0742426767","kakande4@gmail.com")
        val contact12=ContactData("","Tasha","0722426780","tasha30@gmail.com")
        val contactList= listOf(contact1,contact2,contact3,contact4)
        val twtAdapter=ContactsAdapter(contactList)
        binding.rvcontacts.layoutManager=LinearLayoutManager(this)
        binding.rvcontacts.adapter=twtAdapter

    }

}