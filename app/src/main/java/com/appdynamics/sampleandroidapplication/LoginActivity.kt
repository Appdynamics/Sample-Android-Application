package com.appdynamics.sampleandroidapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class LoginActivity : AppCompatActivity() {

    private lateinit var forgotPassword: TextView
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var signUpButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        forgotPassword = findViewById(R.id.forgotPassword)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)

        password.addTextChangedListener(loginTextWatcher)
        username.addTextChangedListener(loginTextWatcher)
    }

    private val loginTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val usernameInput: String = username.getText().toString().trim()
            val passwordInput: String = password.getText().toString().trim()
            loginButton.isEnabled = !usernameInput.isEmpty() && !passwordInput.isEmpty()
        }

        override fun afterTextChanged(s: Editable) {}
    }
}