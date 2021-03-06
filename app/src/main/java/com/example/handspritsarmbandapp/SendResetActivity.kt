package com.example.handspritsarmbandapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.send_reset.*
import kotlinx.android.synthetic.main.activity_sign_up.tv_username
import com.example.handspritsarmbandapp.LoginActivity as LoginActivity1
import kotlinx.android.synthetic.main.activity_sign_up.btn_back_to_login as btn_back_to_login1


class SendResetActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.send_reset)
        auth = FirebaseAuth.getInstance()

        //button functions that either opens a new tab or runs the change password process
        val onClickListener = btn_back_to_login.setOnClickListener {
            startActivity(Intent(this, LoginActivity1::class.java))
            finish()
        }

        btn_send_reset.setOnClickListener {
            changePassword()
        }
    }

    //function that check the textbox against emails on the firebase database and sends an email for a password reset to the mail that corresponds to the user
    private fun changePassword(){
        if (tv_username.text.toString().isEmpty()) {
            tv_username.error = "Please enter email"
            tv_username.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(tv_username.text.toString()).matches()) {
            tv_username.error = "Please enter valid email"
            tv_username.requestFocus()
            return
        }

        auth.sendPasswordResetEmail(tv_username.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                                baseContext, "Email sent.",
                                Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(
                                baseContext, "Could not be sent.",
                                Toast.LENGTH_SHORT).show()
                    }
                }

    }
}