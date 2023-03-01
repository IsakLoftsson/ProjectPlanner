package `is`.hi.hbv601g.projectplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("TAG","onCreate: ")

    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG","onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG","onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG","onPause: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG","onDestroy: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG","onStop: ")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
    }

}