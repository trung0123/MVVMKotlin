package com.example.askbekotlin.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.askbekotlin.R
import com.example.askbekotlin.ui.login.LoginActivity
import com.example.askbekotlin.ui.main.MainActivity
import com.example.askbekotlin.utils.Preference

class SplashScreenActivity : AppCompatActivity() {

    private var token by Preference(Preference.USER_TOKEN, "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        if (token.isEmpty()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
