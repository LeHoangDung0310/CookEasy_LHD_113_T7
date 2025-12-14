package com.example.cookeasy_lhd_113_t7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class DeXuatMonAnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_de_xuat_mon_an)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<TextView>(R.id.tvChonLai).setOnClickListener {
            startActivity(Intent(this, ChonNguyenLieuActivity::class.java))
            finish()
        }

        findViewById<CardView>(R.id.cardMonAn1).setOnClickListener {
            startActivity(Intent(this, ChiTietMonAnActivity::class.java))
        }

        findViewById<CardView>(R.id.cardMonAn2).setOnClickListener {
            startActivity(Intent(this, ChiTietMonAnActivity::class.java))
        }

        findViewById<Button>(R.id.btnXemCachLam1).setOnClickListener {
            startActivity(Intent(this, ChiTietMonAnActivity::class.java))
        }

        findViewById<Button>(R.id.btnXemCachLam2).setOnClickListener {
            startActivity(Intent(this, ChiTietMonAnActivity::class.java))
        }
    }
}