package com.example.testapikotlin


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

/**
 * This is the first on launch loading screen
 * it loads during a fixed value of 4 seconds :
 *  - the main activity (composed of the menu)
 *  - the main activity will then launch the home page
 */

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar!!.hide()

        /** Redirection to the Main page (MainActivity) */
        val runnable = Runnable {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        /** sets up the time of loading for Handler Post */
        Handler().postDelayed(runnable, 4000)
    }
}