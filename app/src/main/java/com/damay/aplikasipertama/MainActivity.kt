package com.damay.aplikasipertama

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Ganti dengan nama layout XML Anda

        // Inisialisasi semua card view
        val cardHome: CardView = findViewById(R.id.cardhome)
        val cardHelpdesk: CardView = findViewById(R.id.cardhelpdesk)
        val cardStaff: CardView = findViewById(R.id.cardstaff)
        val cardGalery: CardView = findViewById(R.id.cardgalery)
        val cardSurvey: CardView = findViewById(R.id.cardsurvey)
        val cardExit: CardView = findViewById(R.id.cardexit)

        // Click listener untuk Home
        cardHome.setOnClickListener {
            // Contoh intent ke activity lain
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // Click listener untuk Helpdesk
        cardHelpdesk.setOnClickListener {
            // Contoh membuka nomor telepon
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0123456789")
            startActivity(intent)
        }

        // Click listener untuk Staff/Pegawai
        cardStaff.setOnClickListener {
            val intent = Intent(this, StaffActivity::class.java)
            startActivity(intent)
        }

        // Click listener untuk Galeri
        cardGalery.setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            startActivity(intent)
        }

        // Click listener untuk Survey
        cardSurvey.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        // Click listener untuk Exit
        cardExit.setOnClickListener {
            showExitConfirmation()
        }
    }

    // Fungsi untuk menampilkan dialog konfirmasi exit
    private fun showExitConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Keluar")
            .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
            .setPositiveButton("Ya") { dialog, which ->
                finishAffinity() // Keluar dari semua activity
            }
            .setNegativeButton("Tidak") { dialog, which ->
                dialog.dismiss()
            }
            .setCancelable(true)
            .show()
    }

    // Handle back button press
    override fun onBackPressed() {
        showExitConfirmation()
    }
}