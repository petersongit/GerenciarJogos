package com.example.gerenciarjogos.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
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
import kotlinx.android.synthetic.main.activity_cadastro_jogos.*

class CadastroJogos : AppCompatActivity() {
    private lateinit var  database: FirebaseDatabase
    private lateinit var  RefDB: DatabaseReference

    private lateinit var storage: FirebaseStorage
    private lateinit var RefStorage:StorageReference
    private lateinit var RefImagem: StorageReference

    lateinit var uriImagem: Uri
    private val CODE_IMG = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_jogos)

        configDataBase()
        configStorage()

        btnSaveJogo.setOnClickListener {
            var jogo = getJogo()


            Log.i("IMAGEM", jogo.imgRef.toString())
            salvarJogo(jogo)
        }

        btnImagem.setOnClickListener {
            abrirGaleria()
        }

    }

    fun configDataBase(){
        database = Firebase.database
        RefDB = database.getReference("jogos")
    }

    fun configStorage(){
        storage = Firebase.storage("gs://gerenciar-jogos.appspot.com")
        RefStorage = storage.reference
    }

    fun getJogo(): Jogo {

        val jogo = Jogo(
            txtCadstroJogoName.text.toString().toUpperCase(),
            txtCadstroJogoCriadoEm.text.toString(),
            txtCadstroJogoDescricao.text.toString(),
            "a")

        return jogo
    }


    fun salvarJogo(jogo: Jogo){
        RefDB.child(jogo.name.toString()).setValue(jogo).addOnSuccessListener {
            SalvarImagem(jogo)
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
    fun abrirGaleria(){
        intent =  Intent(Intent.ACTION_PICK,  MediaStore.Images.Media.INTERNAL_CONTENT_URI)

        val intent = Intent()
        intent.type="image/"
        startActivityForResult(Intent.createChooser(intent, "Captura imagem"), CODE_IMG)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            if(requestCode == 0) {
                if (data != null){
                    uriImagem = data!!.data!!
                    Toast.makeText(this, "Foto selecionada com sucesso!", Toast.LENGTH_LONG).show()
                   // btnImagem.setBackgroundColor(R.color.SteelBlue)
                    //RefImagem.putFile(data!!.data!!)
                }
            }
        }

    }

    fun SalvarImagem(jogo: Jogo){

        if (uriImagem != null){
            var RefDiretorio= RefStorage.child("imagens")
            RefImagem = RefDiretorio.child(jogo.name)


            val uploadTask = RefImagem.putFile(uriImagem)
            uploadTask.continueWithTask { task ->
                if (task.isSuccessful) {

                }
                RefImagem!!.downloadUrl
            }.addOnCompleteListener { task ->

                val downloadUri = task.result
                val url = downloadUri!!.toString()
                    .substring(0, downloadUri.toString().indexOf("&token"))

                RefDB.child(jogo.name.toString()).child("imgRef").setValue(url)

            }

        }

    }
}


