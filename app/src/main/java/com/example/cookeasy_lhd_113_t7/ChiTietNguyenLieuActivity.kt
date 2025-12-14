package com.example.cookeasy_lhd_113_t7

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ChiTietNguyenLieuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_chi_tiet_nguyen_lieu)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<ImageView>(R.id.btnEdit).setOnClickListener {
            // Navigate to edit ingredient
        }

        findViewById<ImageView>(R.id.btnDelete).setOnClickListener {
            showDeleteDialog()
        }
    }

    private fun showDeleteDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_xac_nhan_xoa)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.findViewById<Button>(R.id.btnHuy).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<Button>(R.id.btnXoa).setOnClickListener {
            // Xóa nguyên liệu
            dialog.dismiss()
            finish()
        }

        dialog.show()
    }
}