package com.example.gerenciarjogos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.gerenciarjogos.R
import com.example.gerenciarjogos.model.Usuario
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_cadastro_usuarios.*

class CadastroUsuariosActivity : AppCompatActivity() {
 //   private lateinit var  database: FirebaseFirestore
 //   private lateinit var  myRef: CollectionReference

    //private lateinit var  database: FirebaseDatabase
    //private lateinit var  myRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        //val mStorageRef: StorageReference
        //mStorageRef = FirebaseStorage.getInstance().getReference();

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuarios)



        btnCriarAccount.setOnClickListener{

            config()
            var usuario = getUsuario()
            sendUsuario(usuario)

            Log.i("TESTE", "Antes de pegar os dados")
            var usario = getUsuario()

            Log.i("TESTE", "Antes de enviar os dados")
            sendUsuario(usario)
            Log.i("TESTE", "Depois de enviar os dados")
        }
    }

    fun config(){
        fun config(){
            //database = FirebaseDatabase.getInstance()
            //myRef = database.reference

            database = FirebaseFirestore.getInstance()
            myRef = database.collection("usuario")
        }
    }



    fun getUsuario(): Usuario {
        //val usuario2: MutableMap<String, Any> = HashMap()
        //val usuario: Usuario(txtName.text.toString(); txtEmail.text.toString(); txtPassword.text.toString(); txtPasswordRepeat.text.toString())
        val usuario = Usuario(
            txtName.text.toString(),
            txtEmail.text.toString(),
            txtPassword.text.toString(),
            txtPasswordRepeat.text.toString()
        )

        Log.i("USUARIO", usuario.name)
        Log.i("USUARIO", usuario.email)
        Log.i("USUARIO", usuario.senha)
        Log.i("USUARIO", usuario.senhaConfirmacao)

       // val usuario: Usuario

        //usuario.name = txtName.text.toString()
        //txtEmail.text.toString()
        //txtPassword.text.toString()
        //txtPasswordRepeat.text.toString()


        return usuario





    }


    fun sendUsuario(user: Usuario){

       // val nome = edNomeProd.text.toString()

       // myRef.child("usuario").setValue(user)
        myRef.document("usuario").set(user)

       /*     .set(prod).addOnSuccessListener {
            Log.i(TAG, it.toString())

        }.addOnFailureListener{
            Log.i(TAG, it.toString())
        }*/
    }












    fun getData(): MutableMap<String, Any>{
        val prod: MutableMap<String, Any> = HashMap()


        prod["Nome"] = "Sabonete"
        prod["qtd"] = "1"
        prod["preco"] = "2"

        return prod

    }


}


