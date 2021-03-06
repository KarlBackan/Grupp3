package com.example.handspritsarmbandapp


import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.btn_sign_up
import kotlinx.android.synthetic.main.activity_sign_up.tv_password
import kotlinx.android.synthetic.main.activity_sign_up.tv_username
import kotlinx.android.synthetic.main.nickname_activity.*
import com.example.handspritsarmbandapp.SendResetActivity as SendResetActivity1
import com.example.handspritsarmbandapp.SignUpActivity as SignUpActivity1
import com.example.handspritsarmbandapp.NicknameActivity as NicknameActivity1





class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        //button functions calls either for changing tabs or to run the login process
        btn_forgot_password.setOnClickListener {
            startActivity(Intent(this, SendResetActivity1::class.java))
            finish()
        }
        btn_sign_up.setOnClickListener {
            startActivity(Intent(this, SignUpActivity1::class.java))
            finish()
        }

        btn_log_in.setOnClickListener {
            doLogin()
        }
    }

    //function which checks the text boxes against the firebase database accounts
    private fun doLogin() {
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

        if (tv_password.text.toString().isEmpty()) {
            tv_password.error = "Please enter password"
            tv_password.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(tv_username.text.toString(), tv_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    updateUI(user)
                } else {
                    Toast.makeText(
                        baseContext, "Please verify your email address.",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        //val currentUser = auth.currentUser
        //updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {

        if (currentUser != null) { //user is signed in
            if(currentUser.isEmailVerified) { //email is verified
                if(currentUser.displayName != null){
                    startActivity(Intent(this, DashboardActivity::class.java))
                }
                else{
                    startActivity(Intent(this, NicknameActivity1::class.java))
                }
                finish()
            }
        } else {
            Toast.makeText(
                baseContext, "Login failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}


