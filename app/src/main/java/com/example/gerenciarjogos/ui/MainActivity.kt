package com.example.gerenciarjogos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gerenciarjogos.Adapter.JogosAdapter
import com.example.gerenciarjogos.R
import com.example.gerenciarjogos.model.Jogo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val jogo1 = Jogo("Homem Aranha", "12", "asdsadsadsa")
    val jogo2 = Jogo("FIFA", "14", "asds")

    val jogos: ArrayList<Jogo> = ArrayList<Jogo>()







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jogos.add(jogo1)
        jogos.add(jogo2)
        var adapter = JogosAdapter(jogos)

        recycleJogos.layoutManager = GridLayoutManager(this, 2)
        recycleJogos.adapter = adapter
        recycleJogos.setHasFixedSize(true)



    }
}