package com.dwi.perseminaran

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SeminarResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seminar_result)

        val nama = intent.getStringExtra("NAMA")
        val email = intent.getStringExtra("EMAIL")
        val hp = intent.getStringExtra("HP")
        val gender = intent.getStringExtra("GENDER")
        val seminar = intent.getStringExtra("SEMINAR")

        findViewById<TextView>(R.id.tv_res_nama).text = "Nama: $nama"
        findViewById<TextView>(R.id.tv_res_email).text = "Email: $email"
        findViewById<TextView>(R.id.tv_res_hp).text = "Nomor HP: $hp"
        findViewById<TextView>(R.id.tv_res_gender).text = "Jenis Kelamin: $gender"
        findViewById<TextView>(R.id.tv_res_seminar).text = "Seminar: $seminar"

        findViewById<Button>(R.id.btn_back_to_home).setOnClickListener {
            finish()
        }
    }
}