package com.example.cookeasy_lhd_113_t7

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

import com.google.firebase.database.*

class DanhSachMonAnActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MonAnAdapter
    private val danhSach = mutableListOf<MonAn>()
    private lateinit var databaseRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.man_hinh_danh_sach_mon_an)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            finish()
        }

        findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            startActivity(Intent(this, ThemMonAnActivity::class.java))
        }

        recyclerView = findViewById(R.id.rvMonAn)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MonAnAdapter(danhSach) { monAn ->
            val intent = Intent(this, ChiTietMonAnActivity::class.java)
            intent.putExtra("MON_AN_ID", monAn.id)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        databaseRef = FirebaseDatabase.getInstance().getReference("monAn")
        loadMonAnFromFirebase()
    }

    private fun loadMonAnFromFirebase() {
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                danhSach.clear()
                for (item in snapshot.children) {
                    val monAn = item.getValue(MonAn::class.java)
                    if (monAn != null) danhSach.add(monAn)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý lỗi nếu cần
            }
        })
    }
}