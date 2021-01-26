package com.example.gerenciarjogos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.gerenciarjogos.R
import com.example.gerenciarjogos.model.Jogo
import com.example.gerenciarjogos.model.Usuario
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_cadastro_jogos.*
import kotlinx.android.synthetic.main.activity_cadastro_usuarios.*
import kotlinx.android.synthetic.main.activity_cadastro_usuarios.txtName
import kotlinx.android.synthetic.main.activity_jogos_detalhe.*

class CadastroJogos : AppCompatActivity() {
    private lateinit var  database: FirebaseDatabase
    private lateinit var  myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_jogos)

        config()

        btnSaveJogo.setOnClickListener{
            var jogo = getJogo()
            salvarJogo(jogo)
        }

    }



    fun config(){
        database = Firebase.database
        myRef = database.getReference("jogos")
    }



    fun getJogo(): Jogo {
        val jogo = Jogo(
            txtCadstroJogoName.text.toString(),
            txtCadstroJogoCriadoEm.text.toString(),
            txtCadstroJogoDescricao.text.toString())

        return jogo
    }


    fun salvarJogo(jogo: Jogo){
        Log.i("TESTE", jogo.name)
        myRef.child(jogo.name.toString()).setValue(jogo).addOnSuccessListener {
            Toast.makeText(this, "Jogo Cadastrado com sucesso!", Toast.LENGTH_LONG).show()
            limparCampos()
        }
    }

    fun limparCampos(){
        txtCadstroJogoName.text.clear()
        txtCadstroJogoCriadoEm.text.clear()
        txtCadstroJogoDescricao.text.clear()
    }

}