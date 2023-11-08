package belajar.android.crud_sqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import belajar.android.crud_sqlite.model.ModelMahasiswa

class DatabaseHandler : SQLiteOpenHelper{
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "db_kampus"
        private const val tb_mahasiswa = "tb_mahasiswa"
        private const val tb_mhs_id = "id"
        private const val tb_mhs_nama = "nama"
        private const val tb_mhs_kelas = "kelas"
        private const val CREATE_TABLE_MAHASISWA = "CREATE TABLE $tb_mahasiswa($tb_mhs_id INTEGER PRIMARY KEY, $tb_mhs_nama VARCHAR(100), $tb_mhs_kelas VARCHAR(20));"

    }

    constructor(context: Context) : super(context, DATABASE_NAME, null, DATABASE_VERSION)

    override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(CREATE_TABLE_MAHASISWA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    public fun createMahahasiswa(mdNotif : ModelMahasiswa):Unit{
        var db : SQLiteDatabase = this.writableDatabase
        var values : ContentValues = ContentValues()
        values.put(tb_mhs_id, mdNotif.id)
        values.put(tb_mhs_nama, mdNotif.nama)
        values.put(tb_mhs_kelas, mdNotif.kelas)
        db.insert(tb_mahasiswa, null, values)
        db.close()
    }

    public fun readMahasiswa():List<ModelMahasiswa>{
        var modelList : MutableList<ModelMahasiswa> = mutableListOf<ModelMahasiswa>()
        var query = "SELECT * FROM $tb_mahasiswa"
        var db: SQLiteDatabase = this.readableDatabase
        var cursor: Cursor = db.rawQuery(query,null)

        if(cursor.moveToFirst()){
            do {
                var mdMhasiswa : ModelMahasiswa = ModelMahasiswa()
                mdMhasiswa.id = cursor.getString(0)
                mdMhasiswa.nama = cursor.getString(1)
                mdMhasiswa.kelas = cursor.getString(2)
                modelList.add(mdMhasiswa)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return modelList
    }

    fun updateMahasiswa(mdNotif: ModelMahasiswa):Int{
        var db: SQLiteDatabase = this.writableDatabase
        var values : ContentValues = ContentValues()
        values.put(tb_mhs_nama, mdNotif.nama)
        values.put(tb_mhs_kelas, mdNotif.kelas)

        return db.update(tb_mahasiswa, values, "$tb_mhs_id = ?", arrayOf(mdNotif.id))
    }

    fun deleteMahasiswa(mdNotif: ModelMahasiswa): Int {
        val db = this.writableDatabase
        return db.delete(tb_mahasiswa, "$tb_mhs_id = ?", arrayOf(mdNotif.id))
    }

}