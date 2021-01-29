package com.example.gerenciarjogos.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gerenciarjogos.R
import com.example.gerenciarjogos.model.Jogo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_jogo_detalhe.*


class JogoDetalhe : AppCompatActivity() {
    private lateinit var  database: FirebaseDatabase
    private lateinit var  myRefJogo: DatabaseReference



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

        config()
        buscarJogo(jogo.name.toString())
    }

    fun config(){
        database = Firebase.database
        myRefJogo = database.getReference("jogos")
    }


    fun buscarJogo(nameJogo: String){
        //myRef.setValue(user)
        //Toast.makeText(this, nameJogo, Toast.LENGTH_LONG).show()

        myRefJogo.child(nameJogo).get().addOnSuccessListener {


           // var jogo: Jogo = it.getValue()
        }

    }

}