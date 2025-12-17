package com.example.cookeasy_lhd_113_t7

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MonAnAdapter(
    private val danhSach: List<MonAn>,
    private val onItemClick: (MonAn) -> Unit
) : RecyclerView.Adapter<MonAnAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivIcon: ImageView = view.findViewById(R.id.ivIcon)
        val tvTenMonAn: TextView = view.findViewById(R.id.tvTenMonAn)
        val tvMoTa: TextView = view.findViewById(R.id.tvMoTa)
        val tvThoiGian: TextView = view.findViewById(R.id.tvThoiGian)
        val tvDoKho: TextView = view.findViewById(R.id.tvDoKho)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mon_an, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = danhSach[position]
        holder.tvTenMonAn.text = item.ten
        holder.tvMoTa.text = item.moTa
        holder.tvThoiGian.text = item.thoiGian
        holder.tvDoKho.text = item.doKho
        holder.ivIcon.setImageResource(R.mipmap.ic_launcher)
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount() = danhSach.size
}
