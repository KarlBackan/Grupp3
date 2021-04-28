package com.example.handspritsarmbandapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseUser


class DashboardActivity : AppCompatActivity() {
    //empty so far waiting on designs and buttons
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)

    }


    private fun updateUI(currentUser: FirebaseUser?) {

        if (currentUser != null) {
            if(currentUser.isEmailVerified) {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }
        } else {
            startActivity(Intent(this, DashboardActivity::class.java))

        }
    }

}