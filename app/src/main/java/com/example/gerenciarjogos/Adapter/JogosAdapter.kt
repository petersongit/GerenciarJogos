package com.example.gerenciarjogos.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gerenciarjogos.R
import com.example.gerenciarjogos.model.Jogo
import com.squareup.picasso.Picasso

class JogosAdapter(val listaJogos: ArrayList<Jogo>, val listener: OnClickJogoListener) : RecyclerView.Adapter<JogosAdapter.JogosViewHolder>() {

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
       Picasso.get().load(jogo.imgRef.toString()).into(holder.imagemJogo)
    }

    interface OnClickJogoListener{
        fun onClickJogo(position: Int)
    }

    inner class JogosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
         val nameJogo: TextView
         val dateJogo: TextView
         val imagemJogo: ImageView

        init {
            nameJogo = itemView.findViewById(R.id.txtCardName)
            dateJogo = itemView.findViewById(R.id.txtCardDate)
            imagemJogo = itemView.findViewById(R.id.imgCardLogo)

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onClickJogo(position)
            }
        }


    }

}

