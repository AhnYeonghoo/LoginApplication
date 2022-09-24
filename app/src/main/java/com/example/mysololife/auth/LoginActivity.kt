package com.example.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.mysololife.MainActivity
import com.example.mysololife.R
import com.example.mysololife.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        setContentView(R.layout.activity_login)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.loginBtn.setOnClickListener {

            val email = binding.emailArea.text.toString()
            val password = binding.passwordArea.text.toString()

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)

                    Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_LONG).show()

                } else {
                    Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_LONG).show()

                }
            }


        }
    }
}