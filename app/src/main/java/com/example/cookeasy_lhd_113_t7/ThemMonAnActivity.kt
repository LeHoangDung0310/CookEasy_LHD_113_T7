package com.example.cookeasy_lhd_113_t7


import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import androidx.appcompat.app.AppCompatActivity

class ThemMonAnActivity : AppCompatActivity() {
    private val nguyenLieuList = mutableListOf<String>() // Lưu id nguyên liệu
    private val danhSachNguyenLieu = mutableListOf<NguyenLieu>()
    private lateinit var nguyenLieuAdapter: NguyenLieuAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_them_mon_an)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        // Khởi tạo RecyclerView cho nguyên liệu
        val rvNguyenLieu = findViewById<RecyclerView>(R.id.rvNguyenLieu)
        nguyenLieuAdapter = NguyenLieuAdapter(danhSachNguyenLieu) {}
        rvNguyenLieu.layoutManager = LinearLayoutManager(this)
        rvNguyenLieu.adapter = nguyenLieuAdapter

        // Tải danh sách nguyên liệu từ Firebase
        val firebaseHelper = FirebaseHelper()
        firebaseHelper.layDanhSachNguyenLieu(
            onSuccess = { list ->
                danhSachNguyenLieu.clear()
                danhSachNguyenLieu.addAll(list.filter { it.id.isNotEmpty() })
                nguyenLieuAdapter.notifyDataSetChanged()
            },
            onFailure = { Toast.makeText(this, "Lỗi tải nguyên liệu", Toast.LENGTH_SHORT).show() }
        )

        findViewById<Button>(R.id.btnThemNguyenLieu).setOnClickListener {
            showSelectNguyenLieuDialog()
        }

        // ĐÃ BỎ nút thêm bước làm, không còn xử lý buocLamList nữa

        findViewById<Button>(R.id.btnLuuMonAn).setOnClickListener {
            val etTen = findViewById<TextInputEditText>(R.id.etTenMonAn)
            val etMoTa = findViewById<TextInputEditText>(R.id.etMoTa)
            val etThoiGian = findViewById<TextInputEditText>(R.id.etThoiGian)
            val etDoKho = findViewById<TextInputEditText>(R.id.etDoKho)
            val etCachLam = findViewById<TextInputEditText>(R.id.etCachLam)

            val ten = etTen?.text?.toString()?.trim() ?: ""
            val moTa = etMoTa?.text?.toString()?.trim() ?: ""
            val thoiGian = etThoiGian?.text?.toString()?.trim() ?: ""
            val doKho = etDoKho?.text?.toString()?.trim() ?: ""
            val cachLam = etCachLam?.text?.toString()?.trim() ?: ""

            if (ten.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tên món ăn", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val id = FirebaseDatabase.getInstance().getReference("monAn").push().key ?: System.currentTimeMillis().toString()
            val monAn = MonAn(id, ten, moTa, thoiGian, doKho, nguyenLieuList.toList(), cachLam)
            FirebaseDatabase.getInstance().getReference("monAn").child(id).setValue(monAn)
                .addOnSuccessListener {
                    Toast.makeText(this, "Thêm món ăn thành công", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Lỗi khi thêm món ăn", Toast.LENGTH_SHORT).show()
                }
        }

        findViewById<Button>(R.id.btnHuy).setOnClickListener {
            finish()
        }
    }

    // Hiển thị dialog chọn nguyên liệu từ danh sách
    fun showSelectNguyenLieuDialog() {
        if (danhSachNguyenLieu.isEmpty()) {
            Toast.makeText(this, "Chưa có nguyên liệu nào", Toast.LENGTH_SHORT).show()
            return
        }
        val tenList = danhSachNguyenLieu.map { it.ten }.toTypedArray()
        val checked = BooleanArray(danhSachNguyenLieu.size) { idx ->
            nguyenLieuList.contains(danhSachNguyenLieu[idx].id)
        }
        android.app.AlertDialog.Builder(this)
            .setTitle("Chọn nguyên liệu")
            .setMultiChoiceItems(tenList, checked) { _, which, isChecked ->
                val id = danhSachNguyenLieu[which].id
                if (isChecked) {
                    if (!nguyenLieuList.contains(id)) nguyenLieuList.add(id)
                } else {
                    nguyenLieuList.remove(id)
                }
            }
            .setPositiveButton("Xong") { d, _ ->
                updateNguyenLieuRecyclerView()
                d.dismiss()
            }
            .setNegativeButton("Hủy") { d, _ -> d.dismiss() }
            .show()
    }

    // Cập nhật RecyclerView hiển thị các nguyên liệu đã chọn
    fun updateNguyenLieuRecyclerView() {
        val selected = danhSachNguyenLieu.filter { nguyenLieuList.contains(it.id) }
        nguyenLieuAdapter = NguyenLieuAdapter(selected) {}
        findViewById<RecyclerView>(R.id.rvNguyenLieu).adapter = nguyenLieuAdapter
        findViewById<android.widget.TextView>(R.id.tvSoLuongNguyenLieu).text = "${selected.size} món"
    }

    private fun showAddDialog(title: String, onAdd: (String) -> Unit) {
        val dialog = android.app.AlertDialog.Builder(this)
        dialog.setTitle(title)
        val input = android.widget.EditText(this)
        input.hint = if (title.contains("nguyên liệu")) "Nhập nguyên liệu" else "Nhập bước làm"
        dialog.setView(input)
        dialog.setPositiveButton("Thêm") { d, _ ->
            onAdd(input.text.toString().trim())
            d.dismiss()
        }
        dialog.setNegativeButton("Hủy") { d, _ -> d.dismiss() }
        dialog.show()
    }
}