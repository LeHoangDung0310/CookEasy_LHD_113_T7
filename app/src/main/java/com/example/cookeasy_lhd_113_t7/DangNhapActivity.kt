package com.example.cookeasy_lhd_113_t7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DangNhapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_dang_nhap)

        // Nút đăng nhập
        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            // Chuyển sang Trang chủ
            val intent = Intent(this, TrangChuActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Quên mật khẩu
        findViewById<TextView>(R.id.tvForgotPassword).setOnClickListener {
            startActivity(Intent(this, QuenMatKhauActivity::class.java))
        }

        findViewById<TextView>(R.id.tvRegister).setOnClickListener {
            startActivity(Intent(this, DangKyActivity::class.java))
        }
    }
}