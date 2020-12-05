package dev.luizfelipe.desafiowebservices_digitalhouse.ui.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import dev.luizfelipe.desafiowebservices_digitalhouse.R
import dev.luizfelipe.desafiowebservices_digitalhouse.ui.main.MainActivity

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        val login: MaterialButton = findViewById(R.id.login_button)
        login.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.transition.slide_in_right, R.transition.slide_out_left)
        }

        val sign: TextView = findViewById(R.id.tv_create_account)
        sign.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            overridePendingTransition(R.transition.slide_in_bottom, R.transition.static_animation)
        }
    }
}