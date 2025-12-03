package com.damay.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormMenuActivity : AppCompatActivity() {

    private lateinit var etNamaCustomer: EditText
    private lateinit var btnPesan: Button
    private lateinit var etSoto: EditText
    private lateinit var etTimlo: EditText
    private lateinit var etTeh: EditText
    private lateinit var etJeruk: EditText
    private lateinit var rgTeh : RadioGroup
    private lateinit var rgJeruk : RadioGroup
    private lateinit var rbEsteh: RadioButton
    private lateinit var rbTehhangat: RadioButton
    private lateinit var rbEsjeruk : RadioButton
    private lateinit var rbJerukhangat : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_menu)
        init()

        btnPesan.setOnClickListener {

            val nama = etNamaCustomer.text.toString().trim()
            if (nama.isEmpty()) {
                etNamaCustomer.error = "Nama harus diisi!"
                return@setOnClickListener
            }

            val soto = etSoto.text.toString().ifEmpty { "0" }
            val timlo = etTimlo.text.toString().ifEmpty { "0" }
            val teh = etTeh.text.toString().ifEmpty { "0" }
            val jeruk = etJeruk.text.toString().ifEmpty { "0" }

            val suhuTeh = when (rgTeh.checkedRadioButtonId) {
                R.id.rbEsteh -> "Dingin"
                R.id.rbTehhangat -> "Hangat"
                else -> "Dingin" // default value
            }

            // Mendapatkan pilihan suhu jeruk dari RadioGroup
            val suhuJeruk = when (rgJeruk.checkedRadioButtonId) {
                R.id.rbEsjeruk -> "Dingin"
                R.id.rbJerukhangat -> "Hangat"
                else -> "Dingin" // default value
            }

            val intent = Intent(this, NotaHasil::class.java)
            intent.putExtra("Nama", nama)
            intent.putExtra("Soto", soto)
            intent.putExtra("Timlo", timlo)
            intent.putExtra("Teh", teh)
            intent.putExtra("Jeruk", jeruk)
            intent.putExtra("SuhuTeh", suhuTeh)
            intent.putExtra("SuhuJeruk", suhuJeruk)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun init() {
        etNamaCustomer = findViewById(R.id.etNamaCustomer)
        btnPesan = findViewById(R.id.btnPesan)
        etSoto = findViewById(R.id.etJumlahSoto)
        etTimlo = findViewById(R.id.etJumlahTimlo)
        etTeh = findViewById(R.id.etJumlahTeh)
        etJeruk = findViewById(R.id.etJumlahJeruk)
        rgTeh = findViewById(R.id.rgTeh)
        rgJeruk = findViewById(R.id.rgJeruk)
        rbEsteh = findViewById(R.id.rbEsteh)
        rbTehhangat = findViewById(R.id.rbTehhangat)
        rbEsjeruk = findViewById(R.id.rbEsjeruk)
        rbJerukhangat = findViewById(R.id.rbJerukhangat)
    }
}