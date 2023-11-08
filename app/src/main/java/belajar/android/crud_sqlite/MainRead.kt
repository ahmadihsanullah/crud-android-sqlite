package belajar.android.crud_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import belajar.android.crud_sqlite.adapter.CustomListAdapter
import belajar.android.crud_sqlite.db.DatabaseHandler
import belajar.android.crud_sqlite.model.ModelMahasiswa

class MainRead : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var customListAdapter: CustomListAdapter
    private var dataList: MutableList<ModelMahasiswa> = mutableListOf()
    private lateinit var dbHandler: DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_read)

        dbHandler = DatabaseHandler(this)
        dataList.addAll(dbHandler.readMahasiswa())
        recyclerView = findViewById(R.id.recyclerView)

        setUpRecyclerView()
    }
    private fun setUpRecyclerView(){
        customListAdapter = CustomListAdapter(dataList, object : CustomListAdapter.OnAdapterListener{
            override fun onClick(result: ModelMahasiswa) {
                startActivity(
                    Intent(this@MainRead, MainUpDel::class.java)
                        .putExtra("intent_id", result.id)
                        .putExtra("intent_nama", result.nama)
                        .putExtra("intent_kelas", result.kelas)
                )
            }

        })
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainRead)
            adapter = customListAdapter
            adapter?.notifyDataSetChanged()
        }
    }

    //agar recycler view otomatis terbarui
    override fun onResume() {
        super.onResume()
        dataList.clear()

        dataList.addAll(dbHandler.readMahasiswa())

        setUpRecyclerView()
    }




}