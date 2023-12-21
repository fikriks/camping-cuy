package com.my.campingcuy
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // You can still set up additional initialization code here if needed
    }

    // Define the click handler for the TextView
    fun startTraveling(view: android.view.View) {
        // Start the main activity when the TextView is clicked
        val mainIntent = Intent(this, WelcomeActivity::class.java)
        startActivity(mainIntent)
    }
}