package belajar.android.crud_sqlite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import belajar.android.crud_sqlite.R
import belajar.android.crud_sqlite.model.ModelMahasiswa

class CustomListAdapter(
    private val dataList: MutableList<ModelMahasiswa>,
    private val listener : OnAdapterListener
) : RecyclerView.Adapter<CustomListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView = itemView.findViewById(R.id.iv_profile)
        val nama: TextView = itemView.findViewById(R.id.tv_nama)
        val kelas : TextView = itemView.findViewById(R.id.tv_kelas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.costum_list, parent, false)
    )

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.nama.text = data.nama
        holder.kelas.text = data.kelas
        //ketika itemnya di klik
        holder.itemView.setOnClickListener{
            listener.onClick(data)
        }
    }

    interface OnAdapterListener{
        fun onClick(result: ModelMahasiswa)
    }
}
