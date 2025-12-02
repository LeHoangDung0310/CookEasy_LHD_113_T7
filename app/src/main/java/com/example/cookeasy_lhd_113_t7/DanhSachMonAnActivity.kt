<!-- filepath: d:\AndroidStudioProjects\CookEasy_LHD_113_T7\app\src\main\java\com\example\cookeasy_lhd_113_t7\DanhSachMonAnActivity.kt -->
package com.example.cookeasy_lhd_113_t7

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DanhSachMonAnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_danh_sach_mon_an)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            startActivity(Intent(this, ThemMonAnActivity::class.java))
        }

        // Setup RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rvMonAn)
        recyclerView.layoutManager = LinearLayoutManager(this)
        // recyclerView.adapter = MonAnAdapter(danhSach) { monAn ->
        //     val intent = Intent(this, ChiTietMonAnActivity::class.java)
        //     startActivity(intent)
        // }
    }
}