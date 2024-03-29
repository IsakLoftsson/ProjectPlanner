package `is`.hi.hbv601g.projectplanner

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.r0adkll.slidr.Slidr

class RegisterActivity : AppCompatActivity() {

    private val viewModel: ProjectPlannerViewModel by lazy {
        ViewModelProvider(this,ProjectPlannerViewModel.Factory(this.application)).get(ProjectPlannerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        Slidr.attach(this);

        val mRegisterButton = findViewById<Button>(R.id.register)
        mRegisterButton.isEnabled = true
        mRegisterButton.isClickable = true
        mRegisterButton.setOnClickListener {
            val fn = findViewById<EditText>(R.id.firstname).text.toString()
            val ln = findViewById<EditText>(R.id.lastname).text.toString()
            val email = findViewById<EditText>(R.id.username).text.toString()
            val pw = findViewById<EditText>(R.id.password).text.toString()
            val pn = findViewById<EditText>(R.id.phoneNumber).text.toString().toInt()

            viewModel.registerUser(fn,ln,email,pw,pn)

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }
}