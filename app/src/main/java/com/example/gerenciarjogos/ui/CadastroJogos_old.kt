package com.example.gerenciarjogos.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gerenciarjogos.R
import com.example.gerenciarjogos.model.Jogo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_cadastro_jogos.*
import java.io.ByteArrayOutputStream
import java.io.File


class CadastroJogos_old : AppCompatActivity() {
    private lateinit var  database: FirebaseDatabase
    private lateinit var  RefDB: DatabaseReference

    private lateinit var storage: FirebaseStorage
    private lateinit var RefStorage:StorageReference


    private val CODE_IMG = 0
    lateinit var alertDialog: AlertDialog

    private lateinit var auth: FirebaseAuth

    //var storageRef: StorageReference = storage.getReference()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_jogos)



        //configDataBase()


        btnSaveJogo.setOnClickListener {
            var jogo = getJogo()
            salvarJogo(jogo)
        }



        btnImagem.setOnClickListener {
            //val mountainsRef: StorageReference = storageRef.child("mountains.jpg")
            //gs://gerenciar-jogos.appspot.com


            // val picasso = Picasso.get()
            // var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)

                        abrirGaleria()

        }


    }




    fun configDataBase(){
        database = Firebase.database
        RefDB = database.getReference("jogos")
    }

    fun configStorage(){

        alertDialog = SpotsDialog.Builder().setContext(this).build()
        storage = Firebase.storage("gs://gerenciar-jogos.appspot.com")
        RefStorage = storage.reference

        //storage = FirebaseStorage.getInstance()
        Log.i("TESTE", "AQUI")
        //RefStorage = storage.getReference("aaa")

    }

    fun getJogo(): Jogo {
        val jogo = Jogo(
            txtCadstroJogoName.text.toString(),
            txtCadstroJogoCriadoEm.text.toString(),
            txtCadstroJogoDescricao.text.toString())

        return jogo
    }


    fun salvarJogo(jogo: Jogo){
        TODO("Avaliar")
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
    fun abrirGaleria(){
        configStorage()

      intent =  Intent(Intent.ACTION_PICK,  MediaStore.Images.Media.INTERNAL_CONTENT_URI)

        val intent = Intent()
        intent.type="image/"
        //intent.action = Intent.ACTION_GET_CONTENT
      //  intent.action = Intent.ACTION_PICK
        startActivityForResult(Intent.createChooser(intent, "Captura imagem"), CODE_IMG)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        lateinit var img: Bitmap







        var file = File(data?.dataString.toString())


        var uriFile = Uri.fromFile(file)
        //val stream = FileInputStream(File(data?.dataString.toString()))

     //   val riversRef = storageRef.child("images/${file.lastPathSegment}")

        val baos: ByteArrayOutputStream = ByteArrayOutputStream()



        if (resultCode == Activity.RESULT_OK && data != null){
            var uri = data.data
            img = MediaStore.Images.Media.getBitmap(contentResolver, uri)
        }



        if (img != null){

            var baos: ByteArrayOutputStream = ByteArrayOutputStream()
            img.compress(Bitmap.CompressFormat.JPEG, 10, baos)
            var dadosImagem = baos.toByteArray()


            var imagens= RefStorage.child("imagens")
            var imagemRef= imagens.child("imagens/foto.jpeg")



            Log.i("PATH", file.path)
            alertDialog.show()
            var uploadTask: UploadTask =imagemRef.putFile(data!!.data!!)

            auth.signInAnonymously().addOnCompleteListener(this){
                Log.d("TAG", "signInAnonymously:success")
                val user = auth.currentUser
                imagemRef.putBytes(dadosImagem)
            }
           /* var uploadTask: UploadTask = imagemRef.putBytes(dadosImagem)

            Log.i("STATUS", "CHEGOU")
            uploadTask.addOnFailureListener {
                Log.i("STATUS", "SUCESSO")
            }.addOnSuccessListener { taskSnapshot ->
                Log.i("STATUS", "FALHA")
            }
            Log.i("STATUS", "PASSOU")*/

            //var imageRef = RefStorage.child("imagens").child("Jogo1.jpeg")
            //var uploadTask: UploadTask = imageRef.putBytes(dadosImagem)


            //aaa.setImageBitmap(img)
        }

     /*   Log.i("DATA", data.toString() )
        if (resultCode == Activity.RESULT_OK && data != null){
            Log.i("RESULT", "OK" )
            var file =Uri.fromFile(File((data.toString())))
            val riversRef = RefStorage.child("images/${file.lastPathSegment}")
            riversRef.putFile(file)

            Log.i("FILE", file.toString())
        }*/





      /*  if(reuestCode == CODE_IMG){
            alertDialog.show()
            val uploadTask = RefStorage.putFile(data!!.data!!)
            Log.i("DATA", "AQUI222")
            val task = uploadTask.continueWithTask { task ->
                Log.i("DATA", "AQUI3" )
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
        }*/
    }


    fun updateUI(account: FirebaseUser?) {
        if (account != null) {
            Toast.makeText(this, "U Signed In successfully", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "U Didnt signed in", Toast.LENGTH_LONG).show()
        }
    }
}


