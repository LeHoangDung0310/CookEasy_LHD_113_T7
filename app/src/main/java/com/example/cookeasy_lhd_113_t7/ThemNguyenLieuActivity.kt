package com.example.cookeasy_lhd_113_t7

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ThemNguyenLieuActivity : AppCompatActivity() {
    private lateinit var firebaseHelper: FirebaseHelper
    private lateinit var etTenNguyenLieu: TextInputEditText
    private lateinit var etSoLuong: TextInputEditText
    private lateinit var etDonVi: TextInputEditText
    private lateinit var etNgayHetHan: TextInputEditText
    private lateinit var etGhiChu: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_them_nguyen_lieu)

        firebaseHelper = FirebaseHelper()

        etTenNguyenLieu = findViewById(R.id.etTenNguyenLieu)
        etSoLuong = findViewById(R.id.etSoLuong)
        etDonVi = findViewById(R.id.etDonVi)
        etNgayHetHan = findViewById(R.id.etNgayHetHan)
        etGhiChu = findViewById(R.id.etGhiChu)
        
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
            luuNguyenLieu()
        }

        findViewById<Button>(R.id.btnHuy).setOnClickListener {
            finish()
        }
    }

    private fun luuNguyenLieu() {
        val ten = etTenNguyenLieu.text.toString().trim()
        val soLuong = etSoLuong.text.toString().trim()
        val donVi = etDonVi.text.toString().trim()
        val ngayHetHan = etNgayHetHan.text.toString().trim()
        val ghiChu = etGhiChu.text.toString().trim()

        // Validation
        if (ten.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên nguyên liệu", Toast.LENGTH_SHORT).show()
            return
        }

        if (soLuong.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số lượng", Toast.LENGTH_SHORT).show()
            return
        }

        if (donVi.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đơn vị", Toast.LENGTH_SHORT).show()
            return
        }

        // Tạo đối tượng NguyenLieu
        val nguyenLieu = NguyenLieu(
            ten = ten,
            soLuong = "$soLuong $donVi",
            donVi = donVi,
            ngayHetHan = ngayHetHan,
            ghiChu = ghiChu,
            trangThai = "Còn hạn"
        )

        // Lưu vào Firebase
        firebaseHelper.themNguyenLieu(
            nguyenLieu = nguyenLieu,
            onSuccess = { id ->
                Toast.makeText(this, "Thêm nguyên liệu thành công", Toast.LENGTH_SHORT).show()
                finish()
            },
            onFailure = { error ->
                Toast.makeText(this, "Lỗi: $error", Toast.LENGTH_SHORT).show()
            }
        )
    }
}