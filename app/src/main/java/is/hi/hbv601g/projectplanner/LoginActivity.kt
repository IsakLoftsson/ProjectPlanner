package `is`.hi.hbv601g.projectplanner

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private val viewModel: ProjectPlannerViewModel by lazy {
        ViewModelProvider(this,ProjectPlannerViewModel.Factory(this.application)).get(ProjectPlannerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val mLoginButton = findViewById<Button>(R.id.login)
        mLoginButton.isEnabled = true
        mLoginButton.isClickable = true
        mLoginButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.username)
            val password = findViewById<EditText>(R.id.password)
            if (email.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    val user = viewModel.loginUser(email.text.toString(),password.text.toString())
                    if (user != null) {
                        val sharedPref = this@LoginActivity.getSharedPreferences("prefs",Context.MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor.putLong("userId",user.id)
                        editor.putString("userFirstName",user.firstName)
                        editor.apply()
                        viewModel.successLogin()
                    } else {
                        withContext(Dispatchers.Main) {
                            email.setError("Email or password are incorrect")
                            password.setError("Email or password are incorrect")
                        }
                    }
                }
            } else {
                email.setError("Email or password are incorrect")
                password.setError("Email or password are incorrect")
            }
        }
        viewModel.loginLiveData.observe(this, {
            if (it) {
                val intent = Intent(this, ProjectActivity::class.java)
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            }
        })
        val mRegisterText = findViewById<TextView>(R.id.register)
        mRegisterText.isEnabled = true
        mRegisterText.isClickable = true
        mRegisterText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val sharedPref = this.getSharedPreferences("prefs",Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }
}

