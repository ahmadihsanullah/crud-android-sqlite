package belajar.android.crud_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import belajar.android.crud_sqlite.db.DatabaseHandler

class MainActivity : AppCompatActivity() {
    private lateinit var btnCreate: Button
    private lateinit var btnRead: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCreate = findViewById(R.id.btn_create)
        btnRead = findViewById(R.id.btn_read)

        btnCreate.setOnClickListener { btnCreateListener(it) }
        btnRead.setOnClickListener { btnReadListener(it) }

    }

     private fun btnCreateListener(view: View){
        val goCreate  = Intent(this@MainActivity, MainCreate::class.java)
        startActivity(goCreate)
    }

    private fun btnReadListener(view: View){
        val goRead  = Intent(this@MainActivity, MainRead::class.java)
        startActivity(goRead)
    }
}