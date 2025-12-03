package com.damay.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HasilFormActivity : AppCompatActivity() {


    //memebuat variabel
    private lateinit var tvnama: TextView
    private lateinit var tvnomer: TextView
    private lateinit var tvalamat: TextView
    private lateinit var tvkelamin: TextView
    private lateinit var tvagama: TextView
    private lateinit var tvhobi: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil_form)

        init()

        getData()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun init(){
        tvnama = findViewById(R.id.NamA)
        tvalamat = findViewById(R.id.etAlamatt)
        tvnomer = findViewById(R.id.Nomerr)
        tvkelamin = findViewById(R.id.tvKelamin)
        tvagama = findViewById(R.id.tvAgama)
        tvhobi = findViewById(R.id.tvHobi)
    }
    private fun getData(){
        val nama = intent.getStringExtra("Nama").toString()
        val alamat = intent.getStringExtra("Alamat").toString()
        val nohp = intent.getStringExtra("Nomor").toString()
        val kelamin = intent.getStringExtra("Kelamin").toString()
        val agama = intent.getStringExtra("Agama").toString()
        val hobi = intent.getStringExtra("Hobi").toString()

        tvnama.text = nama
        tvalamat.text = alamat
        tvnomer.text = nohp
        tvkelamin.text = kelamin
        tvagama.text = agama
        tvhobi.text = hobi
    }
}