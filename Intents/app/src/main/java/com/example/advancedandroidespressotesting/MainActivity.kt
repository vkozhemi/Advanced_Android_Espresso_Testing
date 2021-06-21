package com.example.advancedandroidespressotesting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_IDEAS = 1

    private lateinit var nameView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameView = findViewById(R.id.name)
    }

    fun goToIdeaActivity(v: View) {
        val button = v as Button

        val intent = Intent(this, IdeasActivity::class.java)
        intent.putExtra(IdeasActivity.KEY_THEME, button.text)

        startActivityForResult(intent, REQUEST_CODE_IDEAS)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_IDEAS -> {
                nameView.text = data?.getStringExtra(IdeasActivity.KEY_NAME)
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }
}