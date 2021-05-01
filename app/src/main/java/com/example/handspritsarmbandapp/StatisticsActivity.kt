package com.example.handspritsarmbandapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.dashboard_activity.*
import kotlinx.android.synthetic.main.nickname_activity.*


class StatisticsActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statistics_activity)
        auth = FirebaseAuth.getInstance()
        txt_display_name.text = auth.currentUser.displayName
    }
}