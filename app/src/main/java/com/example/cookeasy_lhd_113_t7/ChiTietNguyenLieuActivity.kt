package com.example.cookeasy_lhd_113_t7

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class ChiTietNguyenLieuActivity : AppCompatActivity() {
    private lateinit var firebaseHelper: FirebaseHelper
    private var nguyenLieuId: String = ""
    private var currentNguyenLieu: NguyenLieu? = null

    private lateinit var tvTenNguyenLieu: TextView
    private lateinit var tvTrangThai: TextView
    private lateinit var tvTenNguyenLieuDetail: TextView
    private lateinit var tvSoLuong: TextView
    private lateinit var tvDanhMuc: TextView
    private lateinit var tvNgayHetHan: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_chi_tiet_nguyen_lieu)

        firebaseHelper = FirebaseHelper()

        // Lấy ID từ Intent
        nguyenLieuId = intent.getStringExtra("NGUYEN_LIEU_ID") ?: ""

        // Khởi tạo views
        tvTenNguyenLieu = findViewById(R.id.tvTenNguyenLieu)
        tvTrangThai = findViewById(R.id.tvTrangThai)
        tvTenNguyenLieuDetail = findViewById(R.id.tvTenNguyenLieuDetail)
        tvSoLuong = findViewById(R.id.tvSoLuong)
        tvDanhMuc = findViewById(R.id.tvDanhMuc)
        tvNgayHetHan = findViewById(R.id.tvNgayHetHan)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<ImageView>(R.id.btnEdit).setOnClickListener {
            showEditDialog()
        }

        findViewById<ImageView>(R.id.btnDelete).setOnClickListener {
            showDeleteDialog()
        }

        // Tải dữ liệu
        taiDuLieu()
    }

    private fun taiDuLieu() {
        if (nguyenLieuId.isEmpty()) {
            Toast.makeText(this, "Lỗi: Không tìm thấy ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        firebaseHelper.layNguyenLieuTheoId(
            id = nguyenLieuId,
            onSuccess = { nguyenLieu ->
                if (nguyenLieu != null) {
                    currentNguyenLieu = nguyenLieu
                    hienThiThongTin(nguyenLieu)
                } else {
                    Toast.makeText(this, "Không tìm thấy nguyên liệu", Toast.LENGTH_SHORT).show()
                    finish()
                }
            },
            onFailure = { error ->
                Toast.makeText(this, "Lỗi: $error", Toast.LENGTH_SHORT).show()
                finish()
            }
        )
    }

    private fun hienThiThongTin(nguyenLieu: NguyenLieu) {
        tvTenNguyenLieu.text = nguyenLieu.ten
        tvTrangThai.text = nguyenLieu.trangThai
        tvTenNguyenLieuDetail.text = nguyenLieu.ten
        tvSoLuong.text = nguyenLieu.soLuong
        tvDanhMuc.text = nguyenLieu.danhMuc
        tvNgayHetHan.text = nguyenLieu.ngayHetHan.ifEmpty { "Chưa có" }

        // Set màu cho trạng thái
        when {
            nguyenLieu.trangThai.contains("Hết hạn", ignoreCase = true) -> {
                tvTrangThai.setTextColor(getColor(android.R.color.holo_red_dark))
            }
            nguyenLieu.trangThai.contains("Còn hạn", ignoreCase = true) -> {
                tvTrangThai.setTextColor(getColor(android.R.color.holo_green_dark))
            }
            else -> {
                tvTrangThai.setTextColor(getColor(android.R.color.holo_orange_dark))
            }
        }
    }

    private fun showEditDialog() {
        val nguyenLieu = currentNguyenLieu ?: return

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_chinh_sua_nguyen_lieu)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val etTen = dialog.findViewById<TextInputEditText>(R.id.etTenNguyenLieu)
        val etDanhMuc = dialog.findViewById<TextInputEditText>(R.id.etDanhMuc)
        val etSoLuong = dialog.findViewById<TextInputEditText>(R.id.etSoLuong)
        val etDonVi = dialog.findViewById<TextInputEditText>(R.id.etDonVi)
        val etNgayHetHan = dialog.findViewById<TextInputEditText>(R.id.etNgayHetHan)

        // Điền dữ liệu hiện tại
        etTen.setText(nguyenLieu.ten)
        etDanhMuc.setText(nguyenLieu.danhMuc)
        etSoLuong.setText(nguyenLieu.soLuong.split(" ").getOrNull(0) ?: "")
        etDonVi.setText(nguyenLieu.donVi)
        etNgayHetHan.setText(nguyenLieu.ngayHetHan)

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

        dialog.findViewById<Button>(R.id.btnHuy).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<Button>(R.id.btnLuu).setOnClickListener {
            val ten = etTen.text.toString().trim()
            val danhMuc = etDanhMuc.text.toString().trim()
            val soLuong = etSoLuong.text.toString().trim()
            val donVi = etDonVi.text.toString().trim()
            val ngayHetHan = etNgayHetHan.text.toString().trim()

            if (ten.isEmpty() || danhMuc.isEmpty() || soLuong.isEmpty() || donVi.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Cập nhật đối tượng
            nguyenLieu.ten = ten
            nguyenLieu.danhMuc = danhMuc
            nguyenLieu.soLuong = "$soLuong $donVi"
            nguyenLieu.donVi = donVi
            nguyenLieu.ngayHetHan = ngayHetHan

            // Lưu vào Firebase
            firebaseHelper.capNhatNguyenLieu(
                nguyenLieu = nguyenLieu,
                onSuccess = {
                    Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                    taiDuLieu()
                },
                onFailure = { error ->
                    Toast.makeText(this, "Lỗi: $error", Toast.LENGTH_SHORT).show()
                }
            )
        }

        dialog.show()
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
            firebaseHelper.xoaNguyenLieu(
                id = nguyenLieuId,
                onSuccess = {
                    Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                    finish()
                },
                onFailure = { error ->
                    Toast.makeText(this, "Lỗi: $error", Toast.LENGTH_SHORT).show()
                }
            )
        }

        dialog.show()
    }
}