package com.example.cookeasy_lhd_113_t7

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ChiTietMonAnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_chi_tiet_mon_an)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<ImageView>(R.id.btnEdit).setOnClickListener {
            // Navigate to edit recipe
        }

        findViewById<ImageView>(R.id.btnDelete).setOnClickListener {
            // Show delete dialog
        }

        findViewById<Button>(R.id.btnChinhSua).setOnClickListener {
            // Navigate to edit recipe
        }

        findViewById<Button>(R.id.btnXoa).setOnClickListener {
            // Show confirmation dialog
        }
    }
}