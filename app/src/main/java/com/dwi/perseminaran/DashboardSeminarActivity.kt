package com.dwi.perseminaran

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class DashboardSeminarActivity : AppCompatActivity() {

    data class Seminar(
        val name: String,
        val category: String,
        val date: String,
        val description: String,
        val location: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_seminar)
        val userName = intent.getStringExtra("USER_NAME") ?: "User"
        val tvWelcome = findViewById<TextView>(R.id.tv_welcome_user)
        tvWelcome.text = "Selamat datang, $userName"

        val btnLogout = findViewById<ImageButton>(R.id.btn_logout)
        btnLogout.setOnClickListener {
            Toast.makeText(this, "Logout berhasil", Toast.LENGTH_SHORT).show()
            finish()
        }

        // Setup Bottom Add Button
        val btnAdd = findViewById<ImageView>(R.id.btn_bottom_add)
        btnAdd.setOnClickListener {
            val intent = Intent(this, RegisterSeminarActivity::class.java)
            // Tanpa SELECTED_SEMINAR agar spinner bisa dipilih manual
            startActivity(intent)
        }

        setupSeminarList()
    }

    private fun setupSeminarList() {
        val container = findViewById<LinearLayout>(R.id.seminar_list_container)
        val inflater = LayoutInflater.from(this)

        val listSeminar = listOf(
            Seminar("Kalkulus 1", "Matematika", "19 Mei 2026 | 19:00 WIB", "Membahas materi dasar kalkulus hingga mahir seperti Jerome Polin.", "Lantai 4 UTB"),
            Seminar("Android Master", "Teknologi", "20 Mei 2026 | 10:00 WIB", "Belajar membuat aplikasi Android Neobrutalism yang keren.", "Lab Komputer A"),
            Seminar("UI/UX Design", "Desain", "21 Mei 2026 | 13:00 WIB", "Fundamental desain antarmuka pengguna yang modern.", "Ruang Aula Utama"),
            Seminar("Data Science", "Data", "22 Mei 2026 | 09:00 WIB", "Mengolah data mentah menjadi informasi berharga.", "Gedung Pascasarjana"),
            Seminar("Cyber Security", "Keamanan", "23 Mei 2026 | 15:00 WIB", "Mengenal cara kerja peretas dan cara melawannya.", "Daring (Zoom)"),
            Seminar("AI Generative", "Teknologi", "24 Mei 2026 | 20:00 WIB", "Pemanfaatan kecerdasan buatan dalam kehidupan sehari-hari.", "Ruang Teater")
        )

        for (seminar in listSeminar) {
            val itemView = inflater.inflate(R.layout.item_seminar_nb, container, false)

            itemView.findViewById<TextView>(R.id.tv_seminar_name).text = seminar.name
            itemView.findViewById<TextView>(R.id.tv_seminar_category).text = seminar.category
            itemView.findViewById<TextView>(R.id.tv_seminar_date).text = seminar.date
            itemView.findViewById<TextView>(R.id.tv_seminar_desc).text = seminar.description
            itemView.findViewById<TextView>(R.id.tv_seminar_location).text = seminar.location

            itemView.findViewById<Button>(R.id.btn_item_daftar).setOnClickListener {
                val intent = Intent(this, RegisterSeminarActivity::class.java)
                intent.putExtra("SELECTED_SEMINAR", seminar.name)
                startActivity(intent)
            }

            container.addView(itemView)
        }
    }
}