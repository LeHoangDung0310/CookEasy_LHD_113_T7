package com.example.cookeasy_lhd_113_t7

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DangKyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_dang_ky)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }
}