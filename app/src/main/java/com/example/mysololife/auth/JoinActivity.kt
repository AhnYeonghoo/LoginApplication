package com.example.mysololife.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.mysololife.MainActivity
import com.example.mysololife.R
import com.example.mysololife.databinding.ActivityJoinBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)
        auth = Firebase.auth

        binding.joinBtn.setOnClickListener {

            var isGoToJoin = true
            val email = binding.emailArea.text.toString()
            val password1 = binding.password1.text.toString()
            val password2 = binding.password1.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if (password1.isEmpty()) {
                Toast.makeText(this, "패스워드를 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if (password2.isEmpty()) {
                Toast.makeText(this, "패스워드 체크를 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if (!(password1.equals(password2))) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if (password1.length < 6) {
                Toast.makeText(this, "비밀번호를 6자리 이상으로 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if (isGoToJoin) {
                auth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()

                            val intent = Intent(this, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            startActivity(intent)

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }


    }
}