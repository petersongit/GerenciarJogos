package com.example.gerenciarjogos.Adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gerenciarjogos.R
import com.example.gerenciarjogos.databinding.ActivityMainBinding
import com.example.gerenciarjogos.model.Jogo
import kotlinx.android.synthetic.main.activity_card_jogo.view.*


class JogosAdapter(val listaJogos: ArrayList<Jogo>) : RecyclerView.Adapter<JogosAdapter.JogosViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogosViewHolder {
        val itemJogo = LayoutInflater.from(parent.context).inflate(com.example.gerenciarjogos.R.layout.activity_card_jogo, parent, false)
        return JogosViewHolder(itemJogo)
    }

    override fun getItemCount(): Int {
        return listaJogos.size
    }


    override fun onBindViewHolder(holder: JogosViewHolder, position: Int) {
        var jogo = listaJogos[position]

       holder.nameJogo.text = jogo.name.toString()
       holder.dateJogo.text = jogo.criadoEm.toString()

    }






    inner class JogosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
         val  nameJogo: TextView
         val dateJogo: TextView

        init {
            nameJogo = itemView.findViewById(R.id.txtCardName)
            dateJogo = itemView.findViewById(R.id.txtCardDate)
        }








       // init {
        //    itemView.txtCardName = jogo1.name.toString()
        //    itemView.txtCardDate = jogo1.criadoEm.toString()

            //val a = ActivityMainBinding.inflate(R.layout.activity_card_jogo)
            //nome = itemView.findViewById<TextView>(R.id.ctxtName)
            //date = itemView.findViewById<TextView>(R.id.ctxtdate)
        //}

       // setContentView(R.layout.activity_card_jogo)
        //val a = ActivityMainBinding.inflate(activity_card_jogo)
    }



}