<!-- filepath: d:\AndroidStudioProjects\CookEasy_LHD_113_T7\app\src\main\java\com\example\cookeasy_lhd_113_t7\DanhSachNguyenLieuActivity.kt -->
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
            // Navigate to add ingredient
        }

        // Setup RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rvNguyenLieu)
        recyclerView.layoutManager = LinearLayoutManager(this)
        // recyclerView.adapter = NguyenLieuAdapter(danhSachNguyenLieu)
    }
}