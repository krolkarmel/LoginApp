package com.loginapp

import android.content.Intent
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loginapp.databinding.ActivityLoginBinding
import com.loginapp.databinding.ActivityMainBinding
import java.util.concurrent.Executor

class Login : AppCompatActivity() {
    lateinit var btnFingerprint: Button

    lateinit var executor: Executor
    lateinit var biometricPrompt: BiometricPrompt
    lateinit var promptInfo: BiometricPrompt.PromptInfo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Login"

//        enableEdgeToEdge()
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }


        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.buttonLogin.setOnClickListener {
            val email = binding.textInputEmail.text.toString()
            val password = binding.textInputPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                MainActivity.auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this, Welcome::class.java))
                            finish()
                        }
                    }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext, "Błąd: $errString", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext, "Zalogowano odciskiem palca!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@Login, Welcome::class.java))
                    finish()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Nieudana autoryzacja", Toast.LENGTH_SHORT).show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Logowanie biometryczne")
            .setSubtitle("Użyj odcisku palca do logowania")
            .setNegativeButtonText("Anuluj")
            .build()

        binding.btnFingerprint.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }
}