package com.example.gerenciarjogos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gerenciarjogos.R
import com.example.gerenciarjogos.model.Jogo
import kotlinx.android.synthetic.main.activity_jogo_detalhe.*

class JogoDetalhe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo_detalhe)

    }

    override fun onStart() {
        super.onStart()

        var jogoRecebido: Bundle? = intent.extras
        var jogo: Jogo =  jogoRecebido?.getSerializable("jogo") as Jogo

        txtDetalhejogoName.setText(jogo.name.toString())
        txtDetalhejogoDate.setText(jogo.criadoEm.toString())
        txtDetalhejogoDescription.setText((jogo.Descricao.toString()))
    }

}