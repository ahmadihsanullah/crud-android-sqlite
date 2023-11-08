package belajar.android.crud_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import belajar.android.crud_sqlite.db.DatabaseHandler
import belajar.android.crud_sqlite.model.ModelMahasiswa

class MainUpDel : AppCompatActivity() {
    private lateinit var et_nama : EditText
    private lateinit var et_kelas : EditText
    private lateinit var dbHandler: DatabaseHandler
    private lateinit var btn_update : Button
    private lateinit var btn_delete : Button
    private lateinit var id : String
    private lateinit var nama : String
    private lateinit var kelas : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_up_del)

        dbHandler = DatabaseHandler(this)
        et_nama = findViewById(R.id.et_nama_update)
        et_kelas = findViewById(R.id.et_kelas_update)

        //get intent
        id  = intent.getStringExtra("intent_id").toString()
        nama  = intent.getStringExtra("intent_nama").toString()
        kelas = intent.getStringExtra("intent_kelas").toString()

        et_nama.setText(nama)
        et_kelas.setText(kelas)

        btn_update = findViewById(R.id.btn_update)
        btn_delete = findViewById(R.id.btn_delete)
        update()
        delete()
    }

    private fun update(){
        btn_update.setOnClickListener {
            nama = et_nama.text.toString()
            kelas = et_kelas.text.toString()
            if(nama.equals("")  ){
                et_nama.requestFocus()
                Toast.makeText(this@MainUpDel, "Silahkan isi nama terlebih dahulu", Toast.LENGTH_SHORT).show()
            }else if(kelas.equals("")){
                et_kelas.requestFocus()
                Toast.makeText(this@MainUpDel, "Silahkan isi kelas terlebih dahulu", Toast.LENGTH_SHORT).show()
            }else{
                dbHandler.updateMahasiswa(ModelMahasiswa(id, nama, kelas))
                Toast.makeText(this@MainUpDel, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()

                finish()
            }
        }
    }

    private fun delete(){
        btn_delete.setOnClickListener {
            dbHandler.deleteMahasiswa(ModelMahasiswa(id, nama, kelas))
            Toast.makeText(this@MainUpDel, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
            finish()

        }
    }
}