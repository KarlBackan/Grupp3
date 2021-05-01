package com.example.handspritsarmbandapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DashboardActivity : AppCompatActivity() {
    //empty so far waiting on designs and buttons
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_activity)
    }

    val uid = FirebaseAuth.getInstance().currentUser!!.uid
    val rootRef = FirebaseDatabase.getInstance().reference
    val usersRef = rootRef.child("Users")
    usersRef.child(uid).setValue(user)
}
