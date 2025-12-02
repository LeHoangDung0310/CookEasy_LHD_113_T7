<!-- filepath: d:\AndroidStudioProjects\CookEasy_LHD_113_T7\app\src\main\java\com\example\cookeasy_lhd_113_t7\ThemMonAnActivity.kt -->
package com.example.cookeasy_lhd_113_t7

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ThemMonAnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_them_mon_an)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.btnThemNguyenLieu).setOnClickListener {
            // Show dialog to add ingredient
        }

        findViewById<Button>(R.id.btnThemBuoc).setOnClickListener {
            // Show dialog to add step
        }

        findViewById<Button>(R.id.btnLuuMonAn).setOnClickListener {
            // Save recipe
            finish()
        }

        findViewById<Button>(R.id.btnHuy).setOnClickListener {
            finish()
        }
    }
}