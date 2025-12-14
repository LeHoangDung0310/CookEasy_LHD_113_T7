package com.example.cookeasy_lhd_113_t7

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val intent = Intent(this, DangNhapActivity::class.java)
        startActivity(intent)
        finish()
    }
}