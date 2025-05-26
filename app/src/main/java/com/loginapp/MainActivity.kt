package com.loginapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loginapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        lateinit var auth: FirebaseAuth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets

        auth = FirebaseAuth.getInstance()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
            finish()
        }
        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }


//        binding.SingOut.setOnClickListener {
//            auth.signOut()
//            binding.userDetails.text = updateDate()
//        }
    }
//    override fun onResume(){
//        super.onResume()
//        binding.userDetails.text = updateDate()
//    }
//    private fun updateDate(): String{
//        return "Email: ${auth.currentUser?.email}"
//    }

}