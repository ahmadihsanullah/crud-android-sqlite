package belajar.android.crud_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import belajar.android.crud_sqlite.db.DatabaseHandler
import belajar.android.crud_sqlite.model.ModelMahasiswa

class MainCreate : AppCompatActivity() {
    private lateinit var db : DatabaseHandler
    private lateinit var et_nama : EditText
    private lateinit var et_kelas : EditText
    private var nama : String =""
        get() = field
    private var kelas : String = ""
        get() = field
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_create)

        db = DatabaseHandler(this)

        et_nama = findViewById(R.id.et_nama)
        et_kelas = findViewById(R.id.et_kelas)

        var btn_create = findViewById<Button>(R.id.btn_create)
        btn_create.setOnClickListener{
            nama = et_nama.text.toString()
            kelas = et_kelas.text.toString()

            if(nama.equals("")  ){
                et_nama.requestFocus()
                Toast.makeText(this@MainCreate, "Silahkan isi nama terlebih dahulu", Toast.LENGTH_SHORT).show()
            }else if(kelas.equals("")){
                et_kelas.requestFocus()
                Toast.makeText(this@MainCreate, "Silahkan isi kelas terlebih dahulu", Toast.LENGTH_SHORT).show()
            }else{
                et_nama.text.clear()
                et_kelas.text.clear()
                Toast.makeText(this@MainCreate, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                db.createMahahasiswa(ModelMahasiswa(null, nama, kelas))
            }
        }

    }
}