package com.example.handspritsarmbandapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.btn_sign_up
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.dashboard_activity.*


class DashboardActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)
        auth = FirebaseAuth.getInstance()
        while(auth.currentUser.displayName == null){
            Thread.sleep(500);
        }
        
        txt_display_name.text = auth.currentUser.displayName
        btn_statistics.setOnClickListener {
            startActivity(Intent(this, StatisticsActivity::class.java))
            finish()
        }
    }

    }
