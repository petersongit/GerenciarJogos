package com.example.gerenciarjogos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.gerenciarjogos.R

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            carregarTelaInicial()
            finish()
        }, 3000)
    }

    fun carregarTelaInicial(){
        var intent = Intent(this, login::class.java)
        startActivity(intent)
    }
}