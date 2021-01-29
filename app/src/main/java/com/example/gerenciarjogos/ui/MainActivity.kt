package com.example.gerenciarjogos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gerenciarjogos.Adapter.JogosAdapter
import com.example.gerenciarjogos.R
import com.example.gerenciarjogos.model.Jogo
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), JogosAdapter.OnClickJogoListener{

    var listJogos: ArrayList<Jogo> = ArrayList<Jogo>()

    private lateinit var  database: FirebaseDatabase
    private lateinit var  myRef: DatabaseReference

    private lateinit var storage: FirebaseStorage
    private lateinit var RefStorage: StorageReference
    private lateinit var RefImagem: StorageReference

    private lateinit  var adapter: JogosAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configDataBase()
        configStorage()
        carregarJogos()

        fabCadastrarJogos.setOnClickListener({
            carregarTelaCadsatroJogos()
        })

    }

    override fun onClickJogo(position: Int) {
        carregarTelaDetalheJogo(position)
    }

    fun configStorage(){
        storage = Firebase.storage("gs://gerenciar-jogos.appspot.com")
        RefStorage = storage.reference
    }


    fun carregarTelaDetalheJogo(position: Int){
        var intent = Intent(this, JogoDetalhe::class.java)
        intent.putExtra("jogo", listJogos[position])

        startActivity(intent)
    }



    fun configDataBase(){
        database = Firebase.database
        myRef = database.getReference("jogos")
    }



    fun carregarJogos() {
        myRef.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.i("VALOR", "Nenhum jogo cadastrado")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                listJogos.clear()

                var Firebasejogos: Iterable<DataSnapshot> = snapshot.children
                Firebasejogos.forEach {
                    var criadoEm = snapshot.child(it.key.toString()).child("criadoEm").getValue().toString()
                    var descricao = snapshot.child(it.key.toString()).child("descricao").getValue().toString()
                    var nome = snapshot.child(it.key.toString()).child("name").getValue().toString()
                    var img = snapshot.child(it.key.toString()).child("imgRef").getValue().toString()

                    var jogo = Jogo(nome, criadoEm, descricao, img)
                    listJogos.add(jogo)

                }

                criarListajogos()
            }
        })

    }


    fun criarListajogos(){

        adapter = JogosAdapter(listJogos, this)

        recycleJogos.layoutManager = GridLayoutManager(this, 2)
        recycleJogos.adapter = adapter
        recycleJogos.setHasFixedSize(true)
    }


    fun carregarReferenciaImagem(nome: String): StorageReference {
        var RefDiretorio= RefStorage.child("imagens")
        RefImagem = RefDiretorio.child(nome)
        return RefImagem
    }

    fun carregarTelaCadsatroJogos(){
        var intent = Intent(this, CadastroJogos::class.java)
        startActivity(intent)
    }

}
