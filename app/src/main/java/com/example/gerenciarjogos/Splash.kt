package com.example.gerenciarjogos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
          //  startActivity(Intent(this,ListaHQActivity::class.java))
            carregarTelaInicial()

            finish()
        }, 3000)

    }


    fun carregarTelaInicial(){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}