package com.example.handspritsarmbandapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.tv_username
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.btn_sign_up
import kotlinx.android.synthetic.main.nickname_activity.*
import com.example.handspritsarmbandapp.SignUpActivity as SignUpActivity1


class NicknameActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nickname_activity)
        auth = FirebaseAuth.getInstance()


        btn_sign_up.setOnClickListener {
            startActivity(Intent(this, SignUpActivity1::class.java))
            finish()
        }

        btn_set_display_name.setOnClickListener {
            displayname();
        }


    }

    private fun displayname() {
        if (tv_username.text.toString().isEmpty()) {
            tv_username.error = "Please enter username"
            tv_username.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(tv_username.text.toString(), displayname().toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        user?.sendEmailVerification()
                                ?.addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        startActivity(Intent(this, LoginActivity::class.java))
                                        finish()
                                    }
                                }
                    } else {
                        Toast.makeText(
                                baseContext, "Sign Up failed. Try again after some time.",
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }

    }
}

