package com.dwi.perseminaran

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tabLogin: TextView
    private lateinit var tabRegister: TextView
    private lateinit var containerLogin: View
    private lateinit var containerRegister: View

    // Login views
    private lateinit var etLoginUsername: EditText
    private lateinit var etLoginPassword: EditText
    private lateinit var btnLogin: Button

    // Register views
    private lateinit var etRegUsername: EditText
    private lateinit var etRegEmail: EditText
    private lateinit var etRegPassword: EditText
    private lateinit var etRegConfirmPassword: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var cbEdu: CheckBox
    private lateinit var cbSport: CheckBox
    private lateinit var cbGame: CheckBox
    private lateinit var spinnerCity: Spinner
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupTabs()
        setupSpinner()
        setupListeners()
        setupRealTimeValidation()
    }

    private fun initViews() {
        tabLogin = findViewById(R.id.tab_login)
        tabRegister = findViewById(R.id.tab_register)
        containerLogin = findViewById(R.id.container_login)
        containerRegister = findViewById(R.id.container_register)

        etLoginUsername = findViewById(R.id.et_login_username)
        etLoginPassword = findViewById(R.id.et_login_password)
        btnLogin = findViewById(R.id.btn_login_submit)

        etRegUsername = findViewById(R.id.et_reg_username)
        etRegEmail = findViewById(R.id.et_reg_email)
        etRegPassword = findViewById(R.id.et_reg_password)
        etRegConfirmPassword = findViewById(R.id.et_reg_confirm_password)
        rgGender = findViewById(R.id.rg_gender)
        cbEdu = findViewById(R.id.cb_edu)
        cbSport = findViewById(R.id.cb_sport)
        cbGame = findViewById(R.id.cb_game)
        spinnerCity = findViewById(R.id.spinner_city)
        btnRegister = findViewById(R.id.btn_register_submit)
    }

    private fun setupTabs() {
        tabLogin.setOnClickListener {
            selectTab(true)
        }
        tabRegister.setOnClickListener {
            selectTab(false)
        }
        selectTab(true)
    }

    private fun selectTab(isLogin: Boolean) {
        tabLogin.isSelected = isLogin
        tabRegister.isSelected = !isLogin
        containerLogin.visibility = if (isLogin) View.VISIBLE else View.GONE
        containerRegister.visibility = if (isLogin) View.GONE else View.VISIBLE
    }

    private fun setupSpinner() {
        val cities = arrayOf("Pilih Kota", "Jakarta", "Bandung", "Surabaya", "Yogyakarta", "Medan")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCity.adapter = adapter
    }

    private fun setupListeners() {
        btnLogin.setOnClickListener {
            if (validateLogin()) {
                performLogin()
            }
        }

        btnRegister.setOnClickListener {
            if (validateRegister()) {
                performRegister()
            }
        }
    }

    private fun setupRealTimeValidation() {
        // Login
        etLoginUsername.addTextChangedListener(createTextWatcher { validateLoginUsername() })
        etLoginPassword.addTextChangedListener(createTextWatcher { validateLoginPassword() })

        // Register
        etRegUsername.addTextChangedListener(createTextWatcher { validateRegUsername() })
        etRegEmail.addTextChangedListener(createTextWatcher { validateRegEmail() })
        etRegPassword.addTextChangedListener(createTextWatcher { validateRegPassword() })
        etRegConfirmPassword.addTextChangedListener(createTextWatcher { validateRegConfirmPassword() })
    }

    private fun createTextWatcher(onChanged: () -> Unit) = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { onChanged() }
        override fun afterTextChanged(s: Editable?) {}
    }

    // Validation Functions - Login
    private fun validateLoginUsername(): Boolean {
        val v = etLoginUsername.text.toString().trim()
        return if (v.isEmpty()) {
            etLoginUsername.error = "Username wajib diisi"
            false
        } else {
            etLoginUsername.error = null
            true
        }
    }

    private fun validateLoginPassword(): Boolean {
        val v = etLoginPassword.text.toString().trim()
        return if (v.isEmpty()) {
            etLoginPassword.error = "Password wajib diisi"
            false
        } else {
            etLoginPassword.error = null
            true
        }
    }

    private fun validateLogin(): Boolean {
        return validateLoginUsername() && validateLoginPassword()
    }

    // Validation Functions - Register
    private fun validateRegUsername(): Boolean {
        val v = etRegUsername.text.toString().trim()
        return if (v.isEmpty()) {
            etRegUsername.error = "Username wajib diisi"
            false
        } else {
            etRegUsername.error = null
            true
        }
    }

    private fun validateRegEmail(): Boolean {
        val v = etRegEmail.text.toString().trim()
        return when {
            v.isEmpty() -> {
                etRegEmail.error = "Email wajib diisi"
                false
            }
            !v.contains("@") -> {
                etRegEmail.error = "Email tidak valid (harus mengandung @)"
                false
            }
            else -> {
                etRegEmail.error = null
                true
            }
        }
    }

    private fun validateRegPassword(): Boolean {
        val v = etRegPassword.text.toString().trim()
        return if (v.isEmpty()) {
            etRegPassword.error = "Password wajib diisi"
            false
        } else {
            etRegPassword.error = null
            true
        }
    }

    private fun validateRegConfirmPassword(): Boolean {
        val v = etRegConfirmPassword.text.toString().trim()
        val p = etRegPassword.text.toString().trim()
        return when {
            v.isEmpty() -> {
                etRegConfirmPassword.error = "Konfirmasi password wajib diisi"
                false
            }
            v != p -> {
                etRegConfirmPassword.error = "Password tidak cocok"
                false
            }
            else -> {
                etRegConfirmPassword.error = null
                true
            }
        }
    }

    private fun validateRegister(): Boolean {
        val isFieldsValid = validateRegUsername() && validateRegEmail() && 
                           validateRegPassword() && validateRegConfirmPassword()
        
        if (rgGender.checkedRadioButtonId == -1) {
            showToast("Pilih jenis kelamin")
            return false
        }
        
        if (!cbEdu.isChecked && !cbSport.isChecked && !cbGame.isChecked) {
            showToast("Pilih minimal satu minat")
            return false
        }
        
        if (spinnerCity.selectedItemPosition == 0) {
            showToast("Pilih kota")
            return false
        }
        
        return isFieldsValid
    }

    private fun performLogin() {
        val username = etLoginUsername.text.toString().trim()
        val password = etLoginPassword.text.toString().trim()

        if (username == "admin" && password == "123") {
            showToast("Login berhasil! Selamat datang, $username")
            navigateToDashboard(username)
        } else {
            showToast("Username atau password salah!")
        }
    }

    private fun performRegister() {
        val username = etRegUsername.text.toString().trim()
        showToast("Registrasi berhasil! Selamat datang, $username")
        navigateToDashboard(username)
    }

    private fun navigateToDashboard(username: String) {
        val intent = Intent(this, DashboardSeminarActivity::class.java)
        intent.putExtra("USER_NAME", username)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}