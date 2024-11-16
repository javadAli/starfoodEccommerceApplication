package com.example.starfood.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.starfood.R

class ConnectUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect_us)
        val contactTextView: TextView = findViewById(R.id.contact_number)
        val contactTextViewOffice: TextView = findViewById(R.id.contact_office)
        contactTextView.setOnClickListener {
            val phoneNumber = "02148286" // Phone number to dial
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            startActivity(intent)
        }

        contactTextViewOffice.setOnClickListener {
            val phoneNumber = "02149973000" // Phone number to dial
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            startActivity(intent)
        }
    }
}