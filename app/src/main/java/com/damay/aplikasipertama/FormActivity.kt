package com.damay.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormActivity : AppCompatActivity() {

    // Deklarasi variabel UI
    private lateinit var etNama: EditText
    private lateinit var etNomor: EditText
    private lateinit var etAlamat: EditText
    private lateinit var btnSimpan: Button
    private lateinit var rgKelamin: RadioGroup
    private lateinit var spAgama: Spinner
    private lateinit var cbMembaca: CheckBox
    private lateinit var cbMakan: CheckBox
    private lateinit var cbTidur: CheckBox
    private lateinit var cbOlahraga: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form)

        // Inisialisasi semua view
        init()

        // Tombol simpan ditekan
        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val nomor = etNomor.text.toString().trim()
            val alamat = etAlamat.text.toString().trim()

            // Ambil radio button yang dipilih
            val selectedRadioButton = findViewById<RadioButton>(rgKelamin.checkedRadioButtonId)
            val kelamin = selectedRadioButton?.text?.toString() ?: "Belum dipilih"

            // Ambil agama dari spinner
            val agama = spAgama.selectedItem?.toString() ?: "Belum dipilih"

            // Ambil hobi dari checkbox
            val hobi = mutableListOf<String>()
            if (cbMembaca.isChecked) hobi.add("Membaca")
            if (cbMakan.isChecked) hobi.add("Makan")
            if (cbTidur.isChecked) hobi.add("Tidur")
            if (cbOlahraga.isChecked) hobi.add("Olahraga")

            val hobiString = if (hobi.isEmpty()) "Tidak ada hobi" else hobi.joinToString(", ")

            // Kirim data ke HasilFormActivity
            val keHasil = Intent(this, HasilFormActivity::class.java).apply {
                putExtra("Nama", nama)
                putExtra("Nomor", nomor)
                putExtra("Alamat", alamat)
                putExtra("Kelamin", kelamin)
                putExtra("Agama", agama)
                putExtra("Hobi", hobiString)
            }

            startActivity(keHasil)
        }

        // ✅ FIX crash: gunakan rootView yang pasti ada
        val rootView = findViewById<View>(android.R.id.content)
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Inisialisasi semua elemen dari XML
    private fun init() {
        etNama = findViewById(R.id.etNama)
        etAlamat = findViewById(R.id.etAlamat)
        etNomor = findViewById(R.id.etNomor)
        btnSimpan = findViewById(R.id.btnSimpan)
        rgKelamin = findViewById(R.id.rgKelamin)
        spAgama = findViewById(R.id.spAgama)
        cbMembaca = findViewById(R.id.cbMembaca)
        cbMakan = findViewById(R.id.cbMakan)
        cbTidur = findViewById(R.id.cbTidur)
        cbOlahraga = findViewById(R.id.cbOlahraga)
    }
}