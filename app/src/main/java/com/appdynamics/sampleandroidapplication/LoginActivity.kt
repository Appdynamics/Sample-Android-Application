package com.appdynamics.sampleandroidapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.appdynamics.eumagent.runtime.Instrumentation
import com.google.android.material.textfield.TextInputEditText


class LoginActivity : Activity(), View.OnClickListener {

    private lateinit var forgotPassword: TextView
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var signUpButton: Button
    var TAG = "AppDynamics"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        forgotPassword = findViewById(R.id.forgotPassword)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)

        forgotPassword.setOnClickListener(this)
        loginButton.setOnClickListener(this)

        password.addTextChangedListener(loginTextWatcher)
        username.addTextChangedListener(loginTextWatcher)
    }

    private val loginTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val usernameInput: String = username.text.toString().trim()
            val passwordInput: String = password.text.toString().trim()
            loginButton.isEnabled = !usernameInput.isEmpty() && !passwordInput.isEmpty()
        }

        override fun afterTextChanged(s: Editable) {}
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.forgotPassword -> {
                    val tracker = Instrumentation.beginCall(
                        "Forgot password",
                        "Forgot Password clicked"
                    )
                    Log.d(TAG,"Forgot password clicked")
                    tracker.reportCallEnded()
                }
                R.id.loginButton -> {
                    val usernameText = username.text.toString()
                    Log.d(TAG, "User id: $usernameText")
                    Instrumentation.setUserData("user id", usernameText)
                    val passwordText = password.text.toString()
                    val tracker = Instrumentation.beginCall(
                        "Login",
                        "Login clicked"
                    )
                    Log.d(TAG,"Login clicked")
                    if (passwordText == "admin" && usernameText == "admin"){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    }
                    tracker.reportCallEnded()
                }
            }
        }
    }
}