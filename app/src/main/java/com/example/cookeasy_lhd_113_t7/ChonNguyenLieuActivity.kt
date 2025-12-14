package com.example.cookeasy_lhd_113_t7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChonNguyenLieuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_chon_nguyen_lieu)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Setup RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rvNguyenLieu)
        recyclerView.layoutManager = LinearLayoutManager(this)
        // recyclerView.adapter = NguyenLieuCheckboxAdapter(danhSach)

        findViewById<Button>(R.id.btnTimMonAn).setOnClickListener {
            // Tìm món ăn dựa trên nguyên liệu đã chọn
            startActivity(Intent(this, DeXuatMonAnActivity::class.java))
        }
    }
}