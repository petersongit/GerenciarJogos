package com.example.gerenciarjogos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gerenciarjogos.Adapter.JogosAdapter
import com.example.gerenciarjogos.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var adapter = JogosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleJogos.layoutManager = GridLayoutManager(this, 2)
        recycleJogos.adapter = adapter
        recycleJogos.setHasFixedSize(true)



    }
}