package com.example.cookeasy_lhd_113_t7

import com.google.firebase.database.Exclude

// Model món ăn
// nguyenLieuList: List<String> (chứa id của nguyên liệu, lấy từ danh sách nguyên liệu)
// nguyenLieuList: List<String> (chứa id của nguyên liệu, lấy từ danh sách nguyên liệu)
data class MonAn(
    var id: String = "",
    var ten: String = "",
    var moTa: String = "",
    var thoiGian: String = "",
    var doKho: String = "",
    // Danh sách id nguyên liệu (chỉ lưu id, lấy từ danh sách nguyên liệu)
    var nguyenLieuList: List<String> = emptyList(),
    var cachLam: String = ""
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "ten" to ten,
            "moTa" to moTa,
            "thoiGian" to thoiGian,
            "doKho" to doKho,
            "nguyenLieuList" to nguyenLieuList,
            "cachLam" to cachLam
        )
    }
}
