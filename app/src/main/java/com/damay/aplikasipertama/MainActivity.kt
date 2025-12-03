package com.damay.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Assuming your layout file is named activity_main.xml

        // Initialize all CardViews
        val cardForm = findViewById<CardView>(R.id.cardForm)
        val cardProfile = findViewById<CardView>(R.id.cardProfile)
        val cardMenu = findViewById<CardView>(R.id.cardMenu)
        val cardTemp = findViewById<CardView>(R.id.cardTemp)
        val cardCalc = findViewById<CardView>(R.id.cardCalc)
        val cardExit = findViewById<CardView>(R.id.cardExit)

        // Set click listeners for each CardView
        setupListeners(cardForm, cardProfile, cardMenu, cardTemp, cardCalc, cardExit)
    }

    private fun setupListeners(
        cardForm: CardView,
        cardProfile: CardView,
        cardMenu: CardView,
        cardTemp: CardView,
        cardCalc: CardView,
        cardExit: CardView
    ) {
        // FORM CARD - Navigate to FormMenuActivity
        cardForm.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        // PROFILE CARD - Navigate to ProfileActivity
        cardProfile.setOnClickListener {
            // If you have a ProfileActivity, use this:
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // MENU CARD - Navigate to MenuActivity
        cardMenu.setOnClickListener {
            // If you have a MenuActivity, use this:
            val intent = Intent(this, FormMenuActivity::class.java)
            startActivity(intent)

        }

        // TEMPERATURE CARD - Navigate to TemperatureActivity
        cardTemp.setOnClickListener {
            // If you have a TemperatureActivity, use this:
            val intent = Intent(this, TempConvertActivity::class.java)
            startActivity(intent)

        }

        // CALCULATOR CARD - Navigate to CalculatorActivity
        cardCalc.setOnClickListener {
            // If you have a CalculatorActivity, use this:
            val intent = Intent(this, ConstraintActivity::class.java)
            startActivity(intent)

        }

        // EXIT CARD - Close the app
        cardExit.setOnClickListener {
            Toast.makeText(this, "Keluar aplikasi", Toast.LENGTH_SHORT).show()
            finishAffinity() // This closes all activities in the app
        }
    }
}