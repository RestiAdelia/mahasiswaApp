package com.restiadelia.mahasiswa.screen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.restiadelia.mahasiswa.R
import com.restiadelia.mahasiswa.databinding.ActivityAddMahasiswaBinding
import com.restiadelia.mahasiswa.helper.MahasiswaDatabaseHelper
import com.restiadelia.mahasiswa.model.ModelMahasiswa

class addMahasiswaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddMahasiswaBinding
    private lateinit var db: MahasiswaDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MahasiswaDatabaseHelper(this)

        binding.saveButton.setOnClickListener{
            val nama = binding.namaEditText.text.toString()
            val nim = binding.nimEditText.text.toString()
            val jurusan = binding.jurusanEditText.text.toString()

            val data = ModelMahasiswa(0, nama,nim,jurusan)

            db.insertData(data)
            finish()
            Toast.makeText(this, "Data disimpan", Toast.LENGTH_SHORT).show()
        }
    }
}