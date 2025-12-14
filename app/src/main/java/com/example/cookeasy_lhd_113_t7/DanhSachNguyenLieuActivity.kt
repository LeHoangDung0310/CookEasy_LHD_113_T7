package com.example.cookeasy_lhd_113_t7

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DanhSachNguyenLieuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_danh_sach_nguyen_lieu)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            startActivity(Intent(this, ThemNguyenLieuActivity::class.java))
        }

        // Dữ liệu mẫu
        val danhSachNguyenLieu = listOf(
            NguyenLieu(1, "Cà chua", trangThai = "Còn hạn"),
            NguyenLieu(2, "Cánh gà", trangThai = "Hết hạn sau 2 ngày"),
            NguyenLieu(3, "Hành tây", trangThai = "Còn hạn"),
            NguyenLieu(4, "Thịt bò", trangThai = "Hết hạn sau 1 ngày"),
            NguyenLieu(5, "Trứng gà", trangThai = "Còn hạn"),
            NguyenLieu(6, "Sữa tươi", trangThai = "Hết hạn sau 3 ngày"),
            NguyenLieu(7, "Bơ", trangThai = "Còn hạn"),
            NguyenLieu(8, "Phô mai", trangThai = "Còn hạn")
        )

        // Setup RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rvNguyenLieu)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NguyenLieuAdapter(danhSachNguyenLieu) { nguyenLieu ->
            val intent = Intent(this, ChiTietNguyenLieuActivity::class.java)
            intent.putExtra("TEN_NGUYEN_LIEU", nguyenLieu.ten)
            startActivity(intent)
        }
    }
}