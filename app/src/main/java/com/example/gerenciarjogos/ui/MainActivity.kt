package com.example.gerenciarjogos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gerenciarjogos.Adapter.JogosAdapter
import com.example.gerenciarjogos.R
import com.example.gerenciarjogos.model.Jogo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), JogosAdapter.OnClickJogoListener{
    val jogo1 = Jogo("Homem Aranha", "12", "asdsadsadsa")
    val jogo2 = Jogo("FIFA", "14", "asds")

    val jogos: ArrayList<Jogo> = ArrayList<Jogo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jogos.add(jogo1)
        jogos.add(jogo2)
        var adapter = JogosAdapter(jogos, this)

        recycleJogos.layoutManager = GridLayoutManager(this, 2)
        recycleJogos.adapter = adapter
        recycleJogos.setHasFixedSize(true)

    }

    override fun onClickJogo(position: Int) {
        carregarTelaDetalheJogo(position)
    }


    fun carregarTelaDetalheJogo(position: Int){
        var intent = Intent(this, JogoDetalhe::class.java)
        intent.putExtra("jogo", jogos[position])

        startActivity(intent)
    }
}