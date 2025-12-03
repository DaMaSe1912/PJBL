package com.damay.aplikasipertama

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.NumberFormat
import java.util.Locale

class NotaHasil : AppCompatActivity() {
    private lateinit var tvCustomer: TextView
    private lateinit var tvItem: TextView
    private lateinit var tvJumlah: TextView
    private lateinit var tvSubtotal: TextView
    private lateinit var tvTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_nota_hasil)

        init()
        getData()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun init() {
        tvCustomer = findViewById(R.id.tvCustomer)
        tvItem = findViewById(R.id.tvItem1)
        tvJumlah = findViewById(R.id.tvjml1)
        tvSubtotal = findViewById(R.id.tvSubTotal)
        tvTotal = findViewById(R.id.tvTotal)
    }

    private fun getData() {
        val customer = intent.getStringExtra("Nama").orEmpty()
        val soto = intent.getStringExtra("Soto")?.toIntOrNull() ?: 0
        val timlo = intent.getStringExtra("Timlo")?.toIntOrNull() ?: 0
        val teh = intent.getStringExtra("Teh")?.toIntOrNull() ?: 0
        val jeruk = intent.getStringExtra("Jeruk")?.toIntOrNull() ?: 0

        val suhuTeh = intent.getStringExtra("SuhuTeh") ?: "Dingin"
        val suhuJeruk = intent.getStringExtra("SuhuJeruk") ?: "Dingin"

        val formatter = NumberFormat.getNumberInstance(Locale("in", "ID"))

        val items = mutableListOf<String>()
        val jumlah = mutableListOf<Int>()
        val subtotal = mutableListOf<Int>()

        // Soto
        if (soto > 0) {
            items.add("Soto")
            jumlah.add(soto)
            subtotal.add(soto * 15000)
        }

        // Timlo
        if (timlo > 0) {
            items.add("Timlo")
            jumlah.add(timlo)
            subtotal.add(timlo * 10000)
        }

        // Teh
        if (teh > 0) {
            val namaTeh = if (suhuTeh == "Hangat") "Teh Hangat" else "Es Teh"
            items.add(namaTeh)
            jumlah.add(teh)
            subtotal.add(teh * 5000)
        }

        // Jeruk
        if (jeruk > 0) {
            val namaJeruk = if (suhuJeruk == "Hangat") "Jeruk Hangat" else "Es Jeruk"
            items.add(namaJeruk)
            jumlah.add(jeruk)
            subtotal.add(jeruk * 7000)
        }

        // Jika tidak ada pesanan
        val itemText =
            if (items.isEmpty()) "Tidak ada pesanan"
            else items.joinToString("\n\n")

        val jumlahText =
            if (jumlah.isEmpty()) "-"
            else jumlah.joinToString("\n\n")

        val subtotalText =
            if (subtotal.isEmpty()) "-"
            else subtotal.joinToString("\n\n") {
                "Rp. ${formatter.format(it)}"
            }

        val total = subtotal.sum()
        val totalText = formatter.format(total)

        // Tampilkan ke UI
        tvCustomer.text = "By: $customer"
        tvItem.text = itemText
        tvJumlah.text = jumlahText
        tvSubtotal.text = subtotalText
        tvTotal.text = "TOTAL : Rp. $totalText"
    }
}