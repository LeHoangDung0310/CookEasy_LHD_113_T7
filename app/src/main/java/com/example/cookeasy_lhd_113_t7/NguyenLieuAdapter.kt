package com.example.cookeasy_lhd_113_t7

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NguyenLieuAdapter(
    private val danhSach: List<NguyenLieu>,
    private val onItemClick: (NguyenLieu) -> Unit
) : RecyclerView.Adapter<NguyenLieuAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivIcon: ImageView = view.findViewById(R.id.ivIcon)
        val tvTenNguyenLieu: TextView = view.findViewById(R.id.tvTenNguyenLieu)
        val tvTrangThai: TextView = view.findViewById(R.id.tvTrangThai)
        val tvNgayHetHan: TextView = view.findViewById(R.id.tvNgayHetHan)
        val tvSoLuong: TextView = view.findViewById(R.id.tvSoLuong)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nguyen_lieu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = danhSach[position]
        holder.tvTenNguyenLieu.text = item.ten

        // Hiển thị số lượng
        holder.tvSoLuong.text = item.soLuong

        // Hiển thị ngày hết hạn
        holder.tvNgayHetHan.text = item.ngayHetHan

        // Hiển thị trạng thái
        holder.tvTrangThai.text = item.trangThai
        holder.ivIcon.setImageResource(R.mipmap.ic_launcher)

        // Set màu trạng thái
        when {
            item.trangThai.contains("Hết hạn", ignoreCase = true) -> {
                holder.tvTrangThai.setTextColor(holder.itemView.context.getColor(android.R.color.holo_red_dark))
            }
            item.trangThai.contains("Còn hạn", ignoreCase = true) -> {
                holder.tvTrangThai.setTextColor(holder.itemView.context.getColor(android.R.color.holo_green_dark))
            }
            else -> {
                holder.tvTrangThai.setTextColor(holder.itemView.context.getColor(android.R.color.holo_orange_dark))
            }
        }

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount() = danhSach.size
}
