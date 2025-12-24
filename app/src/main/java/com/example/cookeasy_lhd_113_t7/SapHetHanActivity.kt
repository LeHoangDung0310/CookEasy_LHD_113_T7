package com.example.cookeasy_lhd_113_t7

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SapHetHanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_sap_het_han)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Setup RecyclerView
    }
}