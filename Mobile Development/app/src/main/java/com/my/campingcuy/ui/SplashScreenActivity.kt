package com.my.campingcuy.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.my.campingcuy.databinding.ActivitySplashScreenBinding
import com.my.campingcuy.ui.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("kunci", "Nilai yang akan Anda kirim")
            startActivity(intent)
        }
    }

}