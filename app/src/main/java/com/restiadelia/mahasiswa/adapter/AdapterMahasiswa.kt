package com.restiadelia.mahasiswa.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.restiadelia.mahasiswa.R
import com.restiadelia.mahasiswa.helper.MahasiswaDatabaseHelper
import com.restiadelia.mahasiswa.model.ModelMahasiswa
import com.restiadelia.mahasiswa.screen.UpdateMahasiswaActivity

class AdapterMahasiswa(
    private var datas : List<ModelMahasiswa>,
    context : Context
) : RecyclerView.Adapter<AdapterMahasiswa.DataViewHolder>(){

    private var db : MahasiswaDatabaseHelper = MahasiswaDatabaseHelper(context)

    class DataViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {
        val txtItemNama: TextView = itemView.findViewById(R.id.txtItemNama) // Perbarui ID
        val txtItemNim: TextView = itemView.findViewById(R.id.txtItemNim)   // Perbarui ID
        val txtItemJurusan: TextView = itemView.findViewById(R.id.txtItemJurusan) // Perbarui ID
        val btnEdit: ImageView = itemView.findViewById(R.id.btnEdit)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mahasiswa,
            parent,false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val mahasiswaData = datas[position]
        holder.txtItemNama.text = mahasiswaData.nama
        holder.txtItemNim.text = mahasiswaData.NIM
        holder.txtItemJurusan.text = mahasiswaData.jurusan

        //update

        holder.btnEdit.setOnClickListener{
            val intent = Intent(holder.itemView.context, UpdateMahasiswaActivity::class.java).apply{
                putExtra("data_id", mahasiswaData.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.btnDelete.setOnClickListener{
            AlertDialog.Builder(holder.itemView.context).apply {
                setTitle("Confirm")
                setMessage("Do you want continue?")
                setIcon(R.drawable.baseline_delete_24)

                setPositiveButton("yes"){
                        dialogInterface, i->
                    db.deleteData(mahasiswaData.id)
                    refreshData(db.getAllData())
                    Toast.makeText(holder.itemView.context,"Data berhasil dihapus",
                        Toast.LENGTH_SHORT).show()
                    dialogInterface.dismiss()
                }
                setNeutralButton("No"){
                        dialogInterface, i->
                    dialogInterface.dismiss()
                }
            }.show()
        }

    }

    fun refreshData(newdata : List<ModelMahasiswa>){
        datas = newdata
        notifyDataSetChanged()
    }

}
