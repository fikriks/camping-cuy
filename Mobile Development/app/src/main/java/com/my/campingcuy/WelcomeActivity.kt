package com.my.campingcuy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }

    fun goSignUp(view: android.view.View) {
        // Start the main activity when the TextView is clicked
        val mainIntent = Intent(this, RegisterActivity::class.java)
        startActivity(mainIntent)
    }

    fun goLogin(view: android.view.View) {
        // Start the main activity when the TextView is clicked
        val mainIntent = Intent(this, LoginActivity::class.java)
        startActivity(mainIntent)
    }
}