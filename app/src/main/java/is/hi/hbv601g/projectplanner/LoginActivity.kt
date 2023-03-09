package `is`.hi.hbv601g.projectplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d("TAG","onCreate: ")

        val mLoginButton = findViewById<Button>(R.id.login)
        mLoginButton.isEnabled = true
        mLoginButton.isClickable = true
        mLoginButton.setOnClickListener {
            val intent = Intent(this, ProjectActivity::class.java)
            startActivity(intent)
        }

    }
}