package com.appdynamics.sampleandroidapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.appdynamics.eumagent.runtime.Instrumentation
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

        forgotPassword.setOnClickListener(onClickListener)

        password.addTextChangedListener(loginTextWatcher)
        username.addTextChangedListener(loginTextWatcher)
    }

    private val onClickListener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.forgotPassword -> {
                val tracker = Instrumentation.beginCall(
                    "Forgot password",
                    "Forgot Password clicked"
                )
                Toast.makeText(this,"Forgot password clicked",Toast.LENGTH_SHORT)
                tracker.reportCallEnded()
            }
            R.id.loginButton -> {
                val usernameText = username.text.toString()
                Instrumentation.setUserData("user id", usernameText)
                val passwordText = password.text.toString()
                val tracker = Instrumentation.beginCall(
                    "Login",
                    "Login clicked"
                )
                Toast.makeText(this,"Login clicked",Toast.LENGTH_SHORT)
                if (passwordText == "admin" && usernameText == "admin"){
                    //TODO
                }
                tracker.reportCallEnded()
            }
        }
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