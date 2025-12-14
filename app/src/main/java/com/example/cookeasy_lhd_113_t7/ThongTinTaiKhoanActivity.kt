package com.example.cookeasy_lhd_113_t7

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class ThongTinTaiKhoanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_thong_tin_tai_khoan)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<LinearLayout>(R.id.btnChinhSuaThongTin).setOnClickListener {
            showEditProfileDialog()
        }

        findViewById<LinearLayout>(R.id.btnDoiMatKhau).setOnClickListener {
            showChangePasswordDialog()
        }
    }

    private fun showEditProfileDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_chinh_sua_thong_tin)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val etHoTen = dialog.findViewById<TextInputEditText>(R.id.etHoTen)
        val etEmail = dialog.findViewById<TextInputEditText>(R.id.etEmail)
        val etSoDienThoai = dialog.findViewById<TextInputEditText>(R.id.etSoDienThoai)

        dialog.findViewById<Button>(R.id.btnHuy).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<Button>(R.id.btnLuu).setOnClickListener {
            val hoTen = etHoTen.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val soDienThoai = etSoDienThoai.text.toString().trim()

            if (hoTen.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (soDienThoai.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (soDienThoai.length < 10) {
                Toast.makeText(this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Cập nhật thông tin
            Toast.makeText(this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show()
            
            // Update UI
            findViewById<android.widget.TextView>(R.id.tvTenNguoiDung).text = hoTen
            findViewById<android.widget.TextView>(R.id.tvEmail).text = email
            findViewById<android.widget.TextView>(R.id.tvHoTen).text = hoTen
            findViewById<android.widget.TextView>(R.id.tvEmailInfo).text = email
            findViewById<android.widget.TextView>(R.id.tvSoDienThoai).text = soDienThoai
            
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showChangePasswordDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_doi_mat_khau)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val etMatKhauCu = dialog.findViewById<TextInputEditText>(R.id.etMatKhauCu)
        val etMatKhauMoi = dialog.findViewById<TextInputEditText>(R.id.etMatKhauMoi)
        val etXacNhanMatKhau = dialog.findViewById<TextInputEditText>(R.id.etXacNhanMatKhau)

        dialog.findViewById<Button>(R.id.btnHuy).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<Button>(R.id.btnXacNhan).setOnClickListener {
            val matKhauCu = etMatKhauCu.text.toString().trim()
            val matKhauMoi = etMatKhauMoi.text.toString().trim()
            val xacNhanMatKhau = etXacNhanMatKhau.text.toString().trim()

            if (matKhauCu.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập mật khẩu cũ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (matKhauMoi.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập mật khẩu mới", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (matKhauMoi.length < 6) {
                Toast.makeText(this, "Mật khẩu mới phải có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (xacNhanMatKhau.isEmpty()) {
                Toast.makeText(this, "Vui lòng xác nhận mật khẩu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (matKhauMoi != xacNhanMatKhau) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (matKhauCu == matKhauMoi) {
                Toast.makeText(this, "Mật khẩu mới phải khác mật khẩu cũ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Đổi mật khẩu
            Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.show()
    }
}