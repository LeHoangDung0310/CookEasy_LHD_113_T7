<!-- filepath: d:\AndroidStudioProjects\CookEasy_LHD_113_T7\app\src\main\java\com\example\cookeasy_lhd_113_t7\TrangChuActivity.kt -->
package com.example.cookeasy_lhd_113_t7

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class TrangChuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_trang_chu)

        findViewById<CardView>(R.id.cardDanhSachNguyenLieu).setOnClickListener {
            startActivity(Intent(this, DanhSachNguyenLieuActivity::class.java))
        }

        findViewById<CardView>(R.id.cardNguyenLieuSapHetHan).setOnClickListener {
            startActivity(Intent(this, SapHetHanActivity::class.java))
        }

        findViewById<CardView>(R.id.cardDanhSachMonAn).setOnClickListener {
            startActivity(Intent(this, DanhSachMonAnActivity::class.java))
        }

        findViewById<CardView>(R.id.cardDeXuatMonAn).setOnClickListener {
            // Navigate to suggested recipes
        }
    }
}