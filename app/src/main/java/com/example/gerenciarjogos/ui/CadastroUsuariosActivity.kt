package com.example.gerenciarjogos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.gerenciarjogos.R
import com.example.gerenciarjogos.model.Usuario
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_cadastro_usuarios.*

class CadastroUsuariosActivity : AppCompatActivity() {
    private lateinit var  database: FirebaseDatabase
    private lateinit var  myRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuarios)

        config()

        btnCriarAccount.setOnClickListener{
            var usario = getUsuario()
            sendUsuario(usario)

        }
    }

    fun config(){
         database = Firebase.database
         myRef = database.getReference("usuarios")
    }

    fun getUsuario(): Usuario {
      val usuario = Usuario(
            txtName.text.toString(),
            txtEmail.text.toString(),
            txtPassword.text.toString(),
            txtPasswordRepeat.text.toString()
      )
        return usuario
    }

    fun sendUsuario(user: Usuario){
        //myRef.setValue(user)
        myRef.child(user.name.toString()).setValue(user).addOnSuccessListener {
            Toast.makeText(this, "Usuario Cadastrado com sucesso!", Toast.LENGTH_LONG).show()
            limparCampos()
        }
    }

    fun limparCampos(){
        txtName.text.clear()
        txtEmail.text.clear()
        txtPassword.text.clear()
        txtPasswordRepeat.text.clear()
    }

}


