package com.restiadelia.mahasiswa.screen

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.restiadelia.mahasiswa.databinding.ActivityUpdateMahasiswaBinding
import com.restiadelia.mahasiswa.helper.MahasiswaDatabaseHelper
import com.restiadelia.mahasiswa.model.ModelMahasiswa

class UpdateMahasiswaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUpdateMahasiswaBinding
    private lateinit var db : MahasiswaDatabaseHelper
    private var dataId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MahasiswaDatabaseHelper(this)

        dataId = intent.getIntExtra("data_id",-1)

        if (dataId == -1){
            finish()
            return
        }

        val data = db.getDataById(dataId)
        binding.etEditNama.setText(data.nama)
        binding.etEditNim.setText(data.NIM)
        binding.etEditJurusan.setText(data.jurusan)

        binding.imgEdit.setOnClickListener{
            val newName = binding.etEditNama.text.toString()
            val newNim = binding.etEditNim.text.toString()
            val newJurusan = binding.etEditJurusan.text.toString()

            val updateData = ModelMahasiswa(dataId, newName, newNim, newJurusan)
            db.updateData(updateData)
            finish()
            Toast.makeText(this,"Change Save", Toast.LENGTH_SHORT).show()
        }

    }
}