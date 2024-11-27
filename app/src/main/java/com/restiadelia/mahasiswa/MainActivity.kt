package com.restiadelia.mahasiswa

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager

import com.restiadelia.mahasiswa.adapter.AdapterMahasiswa
import com.restiadelia.mahasiswa.databinding.ActivityMainBinding
import com.restiadelia.mahasiswa.helper.MahasiswaDatabaseHelper
import com.restiadelia.mahasiswa.screen.addMahasiswaActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var MahasiswaAdapter : AdapterMahasiswa
    private lateinit var db : MahasiswaDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = MahasiswaDatabaseHelper(this)
        MahasiswaAdapter = AdapterMahasiswa(db.getAllData(),this)

        binding.mhsRecycleview.layoutManager = LinearLayoutManager(this)
        binding.mhsRecycleview.adapter = MahasiswaAdapter

        binding.addButton.setOnClickListener{
            val intent = Intent(this, addMahasiswaActivity::class.java)
            startActivity(intent)
        }


    }
    override fun onResume() {
        super.onResume()
        val notes = db.getAllData()
        MahasiswaAdapter.refreshData(notes)
    }
}