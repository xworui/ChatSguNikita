package com.example.chatsgu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatsgu.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")
        bind.btnSen.setOnClickListener { myRef.setValue(bind.edMassage.text.toString())
        bind.edMassage.setText("")}
      //  myRef.setValue("Hello, World!")

        onChangeListener(myRef)

    }

    private fun onChangeListener(myRef: DatabaseReference) {
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
             bind.YourResivSrc.append("\n")
             bind.YourResivSrc.append("nikita:  ${snapshot.value.toString()}")
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}