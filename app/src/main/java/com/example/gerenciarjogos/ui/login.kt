package com.example.gerenciarjogos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gerenciarjogos.R
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnTelaInicial.setOnClickListener{
            carregarTelaInicial()
        }

        lblAcessarTelaDeCadastro.setOnClickListener{
            carregarTelaCadastro()
        }
    }



    fun carregarTelaInicial(){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun carregarTelaCadastro(){
        var intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)
    }
}