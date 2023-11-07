package belajar.android.crud_sqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import belajar.android.crud_sqlite.model.ModelMahasiswa

class DatabaseHandler : SQLiteOpenHelper{
    companion object{
        private var DATABASE_VERSION = 1
        private final val DATABASE_NAME ="db_kampus"

        private final val tb_mahasiswa ="tb_mahasiswa"

        private var tb_mhs_id: String = "id"
        private var tb_mhs_nama: String = "nama"
        private var tb_mhs_kelas: String = "kelas"

        private final var CREATE_TABLE_MAHASISWA = """
                CREATE TABLE $tb_mahasiswa(
                $tb_mhs_id INTEGER PRIMARY KEY,
                $tb_mhs_nama VARCHAR(100),
                $tb_mhs_kelas VARCHAR(20)
                );
                """

    }

    constructor(context: Context) : super(context, DATABASE_NAME, null, DATABASE_VERSION)

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(CREATE_TABLE_MAHASISWA)
        }
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

}