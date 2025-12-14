package com.example.cookeasy_lhd_113_t7

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DanhSachNguyenLieuActivity : AppCompatActivity() {
    private lateinit var firebaseHelper: FirebaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var danhSachNguyenLieu = mutableListOf<NguyenLieu>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_danh_sach_nguyen_lieu)

        firebaseHelper = FirebaseHelper()

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            startActivity(Intent(this, ThemNguyenLieuActivity::class.java))
        }

        // Setup RecyclerView
        recyclerView = findViewById<RecyclerView>(R.id.rvNguyenLieu)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Tải dữ liệu từ Firebase
        taiDuLieu()
    }

    private fun taiDuLieu() {
        firebaseHelper.layDanhSachNguyenLieu(
            onSuccess = { danhSach ->
                danhSachNguyenLieu.clear()
                danhSachNguyenLieu.addAll(danhSach)
                capNhatRecyclerView()
            },
            onFailure = { error ->
                Toast.makeText(this, "Lỗi: $error", Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun capNhatRecyclerView() {
        recyclerView.adapter = NguyenLieuAdapter(danhSachNguyenLieu) { nguyenLieu ->
            val intent = Intent(this, ChiTietNguyenLieuActivity::class.java)
            intent.putExtra("NGUYEN_LIEU_ID", nguyenLieu.id)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        taiDuLieu()
    }
}