package com.example.gerenciarjogos.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gerenciarjogos.R
import com.example.gerenciarjogos.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_card_jogo.view.*


class JogosAdapter() : RecyclerView.Adapter<JogosAdapter.JogosViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogosViewHolder {
        val itemJogo = LayoutInflater.from(parent.context).inflate(com.example.gerenciarjogos.R.layout.activity_main, parent, false)
        return JogosViewHolder(itemJogo)
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: JogosViewHolder, position: Int) {
        holder.nome.setText("aaaa")
        holder.date.setText("bbbb")



    }





    inner class JogosViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var nome: TextView
        var date: TextView


        init {
            //val a = ActivityMainBinding.inflate(R.layout.activity_card_jogo)
            nome = view.findViewById<TextView>(R.id.ctxtName)
            date = view.findViewById<TextView>(R.id.ctxtdate)


        }

       // setContentView(R.layout.activity_card_jogo)
        //val a = ActivityMainBinding.inflate(activity_card_jogo)
    }

}