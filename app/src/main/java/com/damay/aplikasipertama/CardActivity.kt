package com.damay.aplikasipertama

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CardActivity : AppCompatActivity() {
    //inisiasi variavel

    private lateinit var homeCard: CardView
    private lateinit var helpdeskCard: CardView
    private lateinit var
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_card)
        //memanggil fun init
        init()
        //Menambahkan kode onClick pada cardview
        cardMenuUtama.setOnClickListener {
            //ketika pengguna melakukan aksi click pada card view
            //maka pada scope inilah kode dieksekusi

            //akan menampilkan pesan yang muncul sebentar
            Toast.makeText( context = this@CardActivity, text ="card view home Diclick",duration = toast.LENGTH_SHORT).show()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left =, systemBars.top =, systemBars.right =, systemBars.bottom =)
            insets


            //langkah 5
            //menambagkan kode onCLick pada cardview
            //ketika pengguna melakukan aksi click pada cardvie
            //maka pada scope inilah kode dieksekusi

        }
    }

    //membuat fun baru
    fun init() {
        //mengisikan variabel
        CardMenuUtama = findViewById(id = R. id.CardMenuUtama)
    }
}