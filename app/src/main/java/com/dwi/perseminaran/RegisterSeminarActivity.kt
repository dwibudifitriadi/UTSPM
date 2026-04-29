package com.dwi.perseminaran

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class RegisterSeminarActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etEmail: EditText
    private lateinit var etHp: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var spinnerSeminar: Spinner
    private lateinit var cbAgree: CheckBox
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_seminar)

        initViews()
        setupSpinner()
        setupRealTimeValidation()
        
        // Cek apakah ada seminar yang dipilih dari Dashboard
        val selectedSeminar = intent.getStringExtra("SELECTED_SEMINAR")
        if (selectedSeminar != null) {
            // Jika lewat tombol "DAFTAR", kunci pilihan seminar
            setSpinnerToSeminar(selectedSeminar)
            spinnerSeminar.isEnabled = false
        } else {
            // Jika lewat tombol "PLUS", bebaskan pilihan
            spinnerSeminar.isEnabled = true
        }

        btnSubmit.setOnClickListener {
            if (validateAll()) {
                showConfirmationDialog()
            }
        }
    }

    private fun initViews() {
        etNama = findViewById(R.id.et_nama)
        etEmail = findViewById(R.id.et_email)
        etHp = findViewById(R.id.et_hp)
        rgGender = findViewById(R.id.rg_gender_seminar)
        spinnerSeminar = findViewById(R.id.spinner_seminar)
        cbAgree = findViewById(R.id.cb_agree)
        btnSubmit = findViewById(R.id.btn_submit_seminar)
    }

    private fun setupSpinner() {
        val seminars = arrayOf(
            "Pilih Seminar",
            "Kalkulus 1",
            "Android Master",
            "UI/UX Design",
            "Data Science",
            "Cyber Security",
            "AI Generative"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, seminars)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSeminar.adapter = adapter
    }

    private fun setSpinnerToSeminar(seminarName: String) {
        val adapter = spinnerSeminar.adapter as ArrayAdapter<String>
        for (i in 0 until adapter.count) {
            if (adapter.getItem(i) == seminarName) {
                spinnerSeminar.setSelection(i)
                break
            }
        }
    }

    private fun setupRealTimeValidation() {
        etNama.addTextChangedListener(createTextWatcher { validateNama() })
        etEmail.addTextChangedListener(createTextWatcher { validateEmail() })
        etHp.addTextChangedListener(createTextWatcher { validateHp() })
    }

    private fun createTextWatcher(onChanged: () -> Unit) = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onChanged()
        }
        override fun afterTextChanged(s: Editable?) {}
    }

    private fun validateNama(): Boolean {
        val nama = etNama.text.toString().trim()
        return if (nama.isEmpty()) {
            etNama.error = "Nama wajib diisi"
            false
        } else {
            etNama.error = null
            true
        }
    }

    private fun validateEmail(): Boolean {
        val email = etEmail.text.toString().trim()
        return when {
            email.isEmpty() -> {
                etEmail.error = "Email wajib diisi"
                false
            }
            !email.contains("@") -> {
                etEmail.error = "Email tidak valid (harus mengandung @)"
                false
            }
            else -> {
                etEmail.error = null
                true
            }
        }
    }

    private fun validateHp(): Boolean {
        val hp = etHp.text.toString().trim()
        return when {
            hp.isEmpty() -> {
                etHp.error = "Nomor HP wajib diisi"
                false
            }
            !hp.all { it.isDigit() } -> {
                etHp.error = "Hanya boleh angka"
                false
            }
            !hp.startsWith("08") -> {
                etHp.error = "Harus diawali dengan 08"
                false
            }
            hp.length < 10 || hp.length > 13 -> {
                etHp.error = "Panjang harus 10-13 digit"
                false
            }
            else -> {
                etHp.error = null
                true
            }
        }
    }

    private fun validateAll(): Boolean {
        val isNamaValid = validateNama()
        val isEmailValid = validateEmail()
        val isHpValid = validateHp()
        
        if (rgGender.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Pilih jenis kelamin", Toast.LENGTH_SHORT).show()
            return false
        }
        
        if (spinnerSeminar.selectedItemPosition == 0) {
            Toast.makeText(this, "Pilih seminar yang ingin diikuti", Toast.LENGTH_SHORT).show()
            return false
        }
        
        if (!cbAgree.isChecked) {
            Toast.makeText(this, "Anda harus menyetujui pernyataan di atas", Toast.LENGTH_SHORT).show()
            return false
        }
        
        return isNamaValid && isEmailValid && isHpValid
    }

    private fun showConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi")
            .setMessage("Apakah data yang Anda isi sudah benar?")
            .setPositiveButton("Ya") { _, _ ->
                val intent = Intent(this, SeminarResultActivity::class.java)
                intent.putExtra("NAMA", etNama.text.toString())
                intent.putExtra("EMAIL", etEmail.text.toString())
                intent.putExtra("HP", etHp.text.toString())
                val selectedGenderId = rgGender.checkedRadioButtonId
                val gender = if (selectedGenderId == R.id.rb_male_seminar) "Laki-laki" else "Perempuan"
                intent.putExtra("GENDER", gender)
                intent.putExtra("SEMINAR", spinnerSeminar.selectedItem.toString())
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Tidak", null)
            .show()
    }
}