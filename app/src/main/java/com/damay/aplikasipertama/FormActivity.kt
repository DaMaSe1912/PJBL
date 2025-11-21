package com.damay.aplikasipertama

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form)
        init()

        //5. menambahkan click listener pada button
        btKirim.setOnClickListener
        //6. Variabel untuk menginput hasil edit text
            val nama = etNama.txt
            val alamat = etAlamat.txt
            val nohp = etNoHP.txt

        //7. berpindah ke actovoty hasil form
         val keHasil = Intent(packageContext = this, cls = FormActivity::class.java)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}