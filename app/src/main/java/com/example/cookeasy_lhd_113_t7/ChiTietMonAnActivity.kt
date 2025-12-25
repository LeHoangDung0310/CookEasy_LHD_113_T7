package com.example.cookeasy_lhd_113_t7

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class ChiTietMonAnActivity : AppCompatActivity() {
    private var monAnId: String = ""
    private var currentMonAn: MonAn? = null
    private val danhSachNguyenLieu = mutableListOf<NguyenLieu>()

    // Only use detail TextViews for display
    private lateinit var tvTenMonAnDetail: android.widget.TextView
    private lateinit var tvThoiGianDetail: android.widget.TextView
    private lateinit var tvDoKhoDetail: android.widget.TextView
    private lateinit var tvMoTaDetail: android.widget.TextView
    private lateinit var tvNguyenLieuDetail: android.widget.TextView
    private lateinit var tvCachLamDetail: android.widget.TextView

    // Header TextViews
    private lateinit var tvDishNameHeader: android.widget.TextView
    private lateinit var tvCookTimeHeader: android.widget.TextView
    private lateinit var tvDoKhoHeader: android.widget.TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_chi_tiet_mon_an)

        monAnId = intent.getStringExtra("MON_AN_ID") ?: ""

        tvTenMonAnDetail = findViewById(R.id.tvDishNameDetail)
        tvThoiGianDetail = findViewById(R.id.tvCookTimeDetail)
        tvDoKhoDetail = findViewById(R.id.tvDoKhoDetail)
        tvMoTaDetail = findViewById(R.id.tvDescription)
        tvNguyenLieuDetail = findViewById(R.id.tvIngredients)
        tvCachLamDetail = findViewById(R.id.tvInstructions)

        tvDishNameHeader = findViewById(R.id.tvDishName)
        tvCookTimeHeader = findViewById(R.id.tvCookTime)
        tvDoKhoHeader = findViewById(R.id.tvDoKho)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }
        findViewById<ImageView>(R.id.btnEdit).setOnClickListener { showEditDialog() }
        findViewById<ImageView>(R.id.btnDelete).setOnClickListener { showDeleteDialog() }

        taiDuLieu()
    }

    private fun taiDuLieu() {
        if (monAnId.isEmpty()) {
            android.widget.Toast.makeText(this, "Không tìm thấy ID món ăn!", android.widget.Toast.LENGTH_LONG).show()
            return
        }
        // Tải danh sách nguyên liệu trước, sau đó lấy món ăn
        val firebaseHelper = FirebaseHelper()
        firebaseHelper.layDanhSachNguyenLieu(
            onSuccess = { list ->
                danhSachNguyenLieu.clear()
                danhSachNguyenLieu.addAll(list.filter { it.id.isNotEmpty() })
                val ref = com.google.firebase.database.FirebaseDatabase.getInstance().getReference("monAn").child(monAnId)
                ref.get().addOnSuccessListener { snapshot ->
                    val monAn = snapshot.getValue(MonAn::class.java)
                    if (monAn != null) {
                        currentMonAn = monAn
                        hienThiThongTin(monAn)
                    } else {
                        android.widget.Toast.makeText(this, "Không tìm thấy dữ liệu món ăn!", android.widget.Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener {
                    android.widget.Toast.makeText(this, "Lỗi khi lấy dữ liệu món ăn!", android.widget.Toast.LENGTH_LONG).show()
                }
            },
            onFailure = { android.widget.Toast.makeText(this, "Lỗi tải nguyên liệu", android.widget.Toast.LENGTH_SHORT).show() }
        )
    }

    private fun hienThiThongTin(monAn: MonAn) {
        // Hiển thị tên nguyên liệu thay vì id
        val nguyenLieuStr = if (danhSachNguyenLieu.isEmpty()) {
            monAn.nguyenLieuList.joinToString("\n")
        } else {
            danhSachNguyenLieu.filter { monAn.nguyenLieuList.contains(it.id) }.joinToString("\n") { it.ten }
        }

        tvTenMonAnDetail.text = monAn.ten
        tvThoiGianDetail.text = monAn.thoiGian
        tvDoKhoDetail.text = monAn.doKho
        tvMoTaDetail.text = monAn.moTa
        tvNguyenLieuDetail.text = nguyenLieuStr
        tvCachLamDetail.text = monAn.cachLam

        // Update header
        tvDishNameHeader.text = monAn.ten
        tvCookTimeHeader.text = monAn.thoiGian
        tvDoKhoHeader.text = monAn.doKho
    }

    private fun showEditDialog() {
        val monAn = currentMonAn ?: return
        val dialog = android.app.Dialog(this)
        dialog.setContentView(R.layout.dialog_chinh_sua_mon_an)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val etTen = dialog.findViewById<android.widget.EditText?>(R.id.etTenMonAn)
        val etMoTa = dialog.findViewById<android.widget.EditText?>(R.id.etMoTa)
        val etThoiGian = dialog.findViewById<android.widget.EditText?>(R.id.etThoiGian)
        val etDoKho = dialog.findViewById<android.widget.EditText?>(R.id.etDoKho)
        val btnChonNguyenLieu = dialog.findViewById<Button?>(R.id.btnChonNguyenLieu)
        val tvNguyenLieuDaChon = dialog.findViewById<android.widget.TextView?>(R.id.tvNguyenLieuDaChon)
        val etCachLam = dialog.findViewById<android.widget.EditText?>(R.id.etCachLam)

        etTen?.setText(monAn.ten)
        etMoTa?.setText(monAn.moTa)
        etThoiGian?.setText(monAn.thoiGian)
        etDoKho?.setText(monAn.doKho)
        // Hiển thị tên nguyên liệu đã chọn
        val selectedNguyenLieu = danhSachNguyenLieu.filter { monAn.nguyenLieuList.contains(it.id) }
        tvNguyenLieuDaChon?.text = selectedNguyenLieu.joinToString(", ") { it.ten }
        tvNguyenLieuDaChon?.tag = monAn.nguyenLieuList.toMutableList()
        etCachLam?.setText(monAn.cachLam)

        // Chọn lại nguyên liệu
        btnChonNguyenLieu?.setOnClickListener {
            if (danhSachNguyenLieu.isEmpty()) {
                android.widget.Toast.makeText(this, "Chưa có nguyên liệu nào", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val tenList = danhSachNguyenLieu.map { it.ten }.toTypedArray()
            val checked = BooleanArray(danhSachNguyenLieu.size) { idx ->
                (tvNguyenLieuDaChon?.tag as? MutableList<String>)?.contains(danhSachNguyenLieu[idx].id) == true
            }
            android.app.AlertDialog.Builder(this)
                .setTitle("Chọn nguyên liệu")
                .setMultiChoiceItems(tenList, checked) { _, which, isChecked ->
                    val id = danhSachNguyenLieu[which].id
                    val tagList = (tvNguyenLieuDaChon?.tag as? MutableList<String>) ?: mutableListOf()
                    if (isChecked) {
                        if (!tagList.contains(id)) tagList.add(id)
                    } else {
                        tagList.remove(id)
                    }
                    tvNguyenLieuDaChon?.tag = tagList
                }
                .setPositiveButton("Xong") { d, _ ->
                    val tagList = (tvNguyenLieuDaChon?.tag as? MutableList<String>) ?: mutableListOf()
                    tvNguyenLieuDaChon?.text = danhSachNguyenLieu.filter { tagList.contains(it.id) }.joinToString(", ") { it.ten }
                    d.dismiss()
                }
                .setNegativeButton("Hủy") { d, _ -> d.dismiss() }
                .show()
        }

        dialog.findViewById<Button>(R.id.btnHuy)?.setOnClickListener { dialog.dismiss() }
        dialog.findViewById<Button>(R.id.btnLuu)?.setOnClickListener {
            val ten = etTen?.text?.toString()?.trim() ?: ""
            val moTa = etMoTa?.text?.toString()?.trim() ?: ""
            val thoiGian = etThoiGian?.text?.toString()?.trim() ?: ""
            val doKho = etDoKho?.text?.toString()?.trim() ?: ""
            val nguyenLieuList = (tvNguyenLieuDaChon?.tag as? MutableList<String>) ?: monAn.nguyenLieuList
            val cachLam = etCachLam?.text?.toString()?.trim() ?: ""

            if (ten.isEmpty()) {
                android.widget.Toast.makeText(this, "Vui lòng nhập tên món ăn", android.widget.Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val capNhat = MonAn(monAn.id, ten, moTa, thoiGian, doKho, nguyenLieuList, cachLam)
            com.google.firebase.database.FirebaseDatabase.getInstance().getReference("monAn").child(monAn.id)
                .setValue(capNhat)
                .addOnSuccessListener {
                    android.widget.Toast.makeText(this, "Cập nhật thành công", android.widget.Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                    taiDuLieu()
                }
                .addOnFailureListener {
                    android.widget.Toast.makeText(this, "Lỗi khi cập nhật", android.widget.Toast.LENGTH_SHORT).show()
                }
        }
        dialog.show()
    }

    private fun showDeleteDialog() {
        val monAn = currentMonAn ?: return
        val dialog = android.app.Dialog(this)
        dialog.setContentView(R.layout.dialog_xac_nhan_xoa)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val tvMessage = dialog.findViewById<android.widget.TextView?>(R.id.tvMessage)
        tvMessage?.text = "Bạn có chắc chắn muốn xóa món ăn \"${monAn.ten}\" khỏi danh sách?"
        dialog.findViewById<Button>(R.id.btnHuy)?.setOnClickListener { dialog.dismiss() }
        dialog.findViewById<Button>(R.id.btnXoa)?.setOnClickListener {
            com.google.firebase.database.FirebaseDatabase.getInstance().getReference("monAn").child(monAn.id)
                .removeValue()
                .addOnSuccessListener {
                    android.widget.Toast.makeText(this, "Đã xóa món ăn", android.widget.Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                    finish()
                }
                .addOnFailureListener {
                    android.widget.Toast.makeText(this, "Lỗi khi xóa", android.widget.Toast.LENGTH_SHORT).show()
                }
        }
        dialog.show()
    }
}