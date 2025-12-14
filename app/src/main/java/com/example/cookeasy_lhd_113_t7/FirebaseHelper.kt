package com.example.cookeasy_lhd_113_t7

import com.google.firebase.database.*

class FirebaseHelper {
    private val database = FirebaseDatabase.getInstance()
    private val nguyenLieuRef = database.getReference("nguyen_lieu")

    // CREATE - Thêm nguyên liệu mới
    fun themNguyenLieu(nguyenLieu: NguyenLieu, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        val id = nguyenLieuRef.push().key
        if (id == null) {
            onFailure("Không thể tạo ID")
            return
        }

        nguyenLieu.id = id

        nguyenLieuRef.child(id).setValue(nguyenLieu)
            .addOnSuccessListener {
                onSuccess(id)
            }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Lỗi không xác định")
            }
    }

    // READ - Lấy tất cả nguyên liệu
    fun layDanhSachNguyenLieu(onSuccess: (List<NguyenLieu>) -> Unit, onFailure: (String) -> Unit) {
        nguyenLieuRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val danhSach = mutableListOf<NguyenLieu>()
                for (childSnapshot in snapshot.children) {
                    val nguyenLieu = childSnapshot.getValue(NguyenLieu::class.java)
                    nguyenLieu?.let { danhSach.add(it) }
                }
                onSuccess(danhSach)
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }
        })
    }

    // READ - Lấy một nguyên liệu theo ID
    fun layNguyenLieuTheoId(id: String, onSuccess: (NguyenLieu?) -> Unit, onFailure: (String) -> Unit) {
        nguyenLieuRef.child(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val nguyenLieu = snapshot.getValue(NguyenLieu::class.java)
                onSuccess(nguyenLieu)
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }
        })
    }

    // UPDATE - Cập nhật nguyên liệu
    fun capNhatNguyenLieu(nguyenLieu: NguyenLieu, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        if (nguyenLieu.id.isEmpty()) {
            onFailure("ID không hợp lệ")
            return
        }

        nguyenLieuRef.child(nguyenLieu.id).setValue(nguyenLieu)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Lỗi không xác định")
            }
    }

    // DELETE - Xóa nguyên liệu
    fun xoaNguyenLieu(id: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        if (id.isEmpty()) {
            onFailure("ID không hợp lệ")
            return
        }

        nguyenLieuRef.child(id).removeValue()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Lỗi không xác định")
            }
    }

    // Hủy listener để tránh memory leak
    fun removeListener() {
        nguyenLieuRef.removeEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {}
            override fun onCancelled(error: DatabaseError) {}
        })
    }
}
