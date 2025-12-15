package com.example.cookeasy_lhd_113_t7

import com.google.firebase.database.Exclude

data class NguyenLieu(
    var id: String = "",
    var ten: String = "",
    var hinhAnh: String = "", // URL ảnh hoặc tên icon
    var soLuong: String = "",
    var donVi: String = "",
    var ngayHetHan: String = "",
    var ghiChu: String = "",
    var trangThai: String = "Còn hạn"
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "ten" to ten,
            "hinhAnh" to hinhAnh,
            "soLuong" to soLuong,
            "donVi" to donVi,
            "ngayHetHan" to ngayHetHan,
            "ghiChu" to ghiChu,
            "trangThai" to trangThai
        )
    }
}
