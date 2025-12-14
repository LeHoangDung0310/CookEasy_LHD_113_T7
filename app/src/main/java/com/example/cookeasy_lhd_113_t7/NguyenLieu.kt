package com.example.cookeasy_lhd_113_t7

data class NguyenLieu(
    val id: Int,
    val ten: String,
    val icon: Int = R.mipmap.ic_launcher,
    val soLuong: String = "",
    val ngayHetHan: String = "",
    val trangThai: String = "Còn hạn"
)
