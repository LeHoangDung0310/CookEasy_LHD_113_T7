<!-- filepath: d:\AndroidStudioProjects\CookEasy_LHD_113_T7\app\src\main\java\com\example\cookeasy_lhd_113_t7\SapHetHanActivity.kt -->
package com.example.cookeasy_lhd_113_t7

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SapHetHanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_sap_het_han)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            // Navigate to add recipe
        }

        // Setup RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rvSapHetHan)
        recyclerView.layoutManager = LinearLayoutManager(this)
        // recyclerView.adapter = SapHetHanAdapter(danhSach)
    }
}