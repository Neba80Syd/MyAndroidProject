package com.example.seulle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val signUpTextView = findViewById<TextView>(R.id.signUpTextView)

        val signedUpName = intent.getStringExtra("NAME")
        val signedUpEmail = intent.getStringExtra("EMAIL")
        val signedUpPassword = intent.getStringExtra("PASSWORD")

        if (signedUpName != null && signedUpEmail != null) {
            nameEditText.setText(signedUpName)
            emailEditText.setText(signedUpEmail)
        }

        loginButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (name == signedUpName && email == signedUpEmail && password == signedUpPassword) {
                val intent = Intent(this, LandingActivity::class.java).apply {
                    putExtra("NAME", name)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid credentials.", Toast.LENGTH_LONG).show()
            }
        }

        signUpTextView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}