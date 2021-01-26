package com.example.gerenciarjogos.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gerenciarjogos.R
import com.example.gerenciarjogos.model.Jogo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_cadastro_jogos.*



class CadastroJogos : AppCompatActivity() {
    private lateinit var  database: FirebaseDatabase
    private lateinit var  RefDB: DatabaseReference

    private lateinit var storage: FirebaseStorage
    private lateinit var RefStorage:StorageReference

    private val CODE_IMG = 1000
    lateinit var alertDialog: AlertDialog


    //var storageRef: StorageReference = storage.getReference()


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_jogos)

        configDataBase()
        configStorage()

        btnSaveJogo.setOnClickListener {
            var jogo = getJogo()
            salvarJogo(jogo)
        }



        btnImagem.setOnClickListener {
            //val mountainsRef: StorageReference = storageRef.child("mountains.jpg")
            //gs://gerenciar-jogos.appspot.com


            // val picasso = Picasso.get()
            // var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)

            setIntents()

        }


    }




        fun configDataBase(){
        database = Firebase.database
        RefDB = database.getReference("jogos")
    }

    fun configStorage(){
        storage = Firebase.storage
        RefStorage = storage.reference
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
        RefDB.child(jogo.name.toString()).setValue(jogo).addOnSuccessListener {
            Toast.makeText(this, "Jogo Cadastrado com sucesso!", Toast.LENGTH_LONG).show()
            limparCampos()
        }
    }

    fun limparCampos(){
        txtCadstroJogoName.text.clear()
        txtCadstroJogoCriadoEm.text.clear()
        txtCadstroJogoDescricao.text.clear()
    }













    //Configura a itent para obter imagem da galeria
    fun setIntents(){
        val intent = Intent()
        intent.type="image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Captura imagem"), CODE_IMG)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.i("TESTE RESQUEST", requestCode.toString())
        Log.i("TESTE RESLT", resultCode.toString())


        if(requestCode == CODE_IMG){
            alertDialog.show()
            val uploadTask = RefStorage.putFile(data!!.data!!)
            val task = uploadTask.continueWithTask { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Chegando", Toast.LENGTH_SHORT).show()
                }
                RefStorage!!.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    val url = downloadUri!!.toString()
                        .substring(0, downloadUri.toString().indexOf("&token"))
                    Log.i("Link Direto ", url)
                    alertDialog.dismiss()
                    //Picasso.get().load(url).into(ivRec)
                }
            }
        }
    }
}


