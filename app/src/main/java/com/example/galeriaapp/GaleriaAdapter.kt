package com.example.galeriaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GaleriaAdapter(
    private val fotos: List<Foto>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<GaleriaAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_foto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foto = fotos[position]

        Glide.with(holder.itemView.context)
            .load(foto.urlFoto)
            .into(holder.itemView.findViewById<ImageView>(R.id.ivCuadro))

        holder.itemView.setOnClickListener {
            onItemClick(foto.urlFoto)
        }
    }

    override fun getItemCount(): Int = fotos.size
}