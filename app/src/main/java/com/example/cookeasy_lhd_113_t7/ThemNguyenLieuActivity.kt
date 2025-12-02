<!-- filepath: d:\AndroidStudioProjects\CookEasy_LHD_113_T7\app\src\main\java\com\example\cookeasy_lhd_113_t7\ThemNguyenLieuActivity.kt -->
package com.example.cookeasy_lhd_113_t7

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class ThemNguyenLieuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_them_nguyen_lieu)

        val etNgayHetHan = findViewById<TextInputEditText>(R.id.etNgayHetHan)
        
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Date picker
        etNgayHetHan.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, day ->
                    etNgayHetHan.setText("$day/${month + 1}/$year")
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        findViewById<Button>(R.id.btnLuuNguyenLieu).setOnClickListener {
            // Lưu nguyên liệu
            finish()
        }

        findViewById<Button>(R.id.btnHuy).setOnClickListener {
            finish()
        }
    }
}