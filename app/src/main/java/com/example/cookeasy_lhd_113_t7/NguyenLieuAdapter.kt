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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nguyen_lieu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = danhSach[position]
        holder.tvTenNguyenLieu.text = item.ten
        
        // Hiển thị trạng thái với số lượng nếu có
        val trangThaiText = if (item.soLuong.isNotEmpty()) {
            "${item.soLuong} - ${item.trangThai}"
        } else {
            item.trangThai
        }
        holder.tvTrangThai.text = trangThaiText
        holder.ivIcon.setImageResource(R.mipmap.ic_launcher)
        
        // Set màu trang thái
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
            val context = holder.itemView.context
            val intent = Intent(context, ChiTietNguyenLieuActivity::class.java)
            intent.putExtra("NGUYEN_LIEU_ID", item.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = danhSach.size
}
