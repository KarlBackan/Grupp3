package com.example.handspritsarmbandapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.nickname_activity.*


class NicknameActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nickname_activity)
        auth = FirebaseAuth.getInstance()

        btn_set_display_name.setOnClickListener {
            createDisplayName()
        }

    }
    private fun createDisplayName(){
        if (tv_display_name.text.toString().isEmpty()) {
            tv_username.error = "Please enter email"
            tv_username.requestFocus()
            return
        }
        val user = auth.currentUser
        val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(tv_display_name.text.toString()).build()
        user.updateProfile(profileUpdates)
        startActivity(Intent(this, DashboardActivity::class.java))

    }




}

