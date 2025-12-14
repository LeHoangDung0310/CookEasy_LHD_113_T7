package com.example.cookeasy_lhd_113_t7

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class TrangChuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_trang_chu)

        findViewById<ImageView>(R.id.btnSettings).setOnClickListener {
            showSettingsDialog()
        }

        findViewById<CardView>(R.id.cardDanhSachNguyenLieu).setOnClickListener {
            startActivity(Intent(this, DanhSachNguyenLieuActivity::class.java))
        }

        findViewById<CardView>(R.id.cardNguyenLieuSapHetHan).setOnClickListener {
            startActivity(Intent(this, SapHetHanActivity::class.java))
        }

        findViewById<CardView>(R.id.cardDanhSachMonAn).setOnClickListener {
            startActivity(Intent(this, DanhSachMonAnActivity::class.java))
        }

        findViewById<CardView>(R.id.cardDeXuatMonAn).setOnClickListener {
            startActivity(Intent(this, ChonNguyenLieuActivity::class.java))
        }
    }

    private fun showSettingsDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_cai_dat)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.findViewById<LinearLayout>(R.id.btnThongTinTaiKhoan).setOnClickListener {
            dialog.dismiss()
            startActivity(Intent(this, ThongTinTaiKhoanActivity::class.java))
        }

        dialog.findViewById<LinearLayout>(R.id.btnDangXuat).setOnClickListener {
            dialog.dismiss()
            // Đăng xuất - quay về màn hình đăng nhập
            val intent = Intent(this, DangNhapActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        dialog.findViewById<Button>(R.id.btnDong).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}