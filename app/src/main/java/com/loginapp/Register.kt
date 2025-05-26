package com.loginapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar
import com.loginapp.databinding.ActivityRegisterBinding


class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Register"


        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.buttonFinish.setOnClickListener {
            val email = binding.textInputEmail.text.toString()
            val password = binding.textInputPassword.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty()){
                MainActivity.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(it.isSuccessful){
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }


    }


}