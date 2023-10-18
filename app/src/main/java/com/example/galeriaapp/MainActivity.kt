package com.example.galeriaapp

import android.os.Bundle
import android.view.View

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    // para el imageView que agrandaremos
    private lateinit var imgGrande: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.foto)

        // la canttidad de fotos
        val numFotos = 100
        // si no lo pongo mutable no me deja añadir las fotos
        val listaFotos = mutableListOf<Foto>()

        // Para las fotitos aleatorias ir metiendolas en la lista
        for (i in 0 until numFotos) {
            val random = (0..100).random() // numero del 0 al 100 aleatorios
            val urlFoto = "https://loremflickr.com/320/240/horse?random=$random" // añadimos el random a la url
            val foto = Foto(urlFoto)
            listaFotos.add(foto)
        }
        // configura el recycler para que utilice el grid
        val recyclerView: RecyclerView = findViewById(R.id.vistaFotos)
        // accede al diseño de organizar los elementos, y hace que tenga 3 columnas en la filaa
        recyclerView.layoutManager = GridLayoutManager(this, 3)


        // Configura el adaptador del recyvler para que utilice galeria adapter, que llama a expandirFoto con la URL de la foto
        recyclerView.adapter = GaleriaAdapter(listaFotos, this::expandirFoto)
        // hace referencia al id imageview para usarlo
        imgGrande = findViewById(R.id.imgGrande)
        // al darle a expanded image view llama a contraerFoto y se cierra, se pone en GONE
        imgGrande.setOnClickListener {
            contraerFoto()
        }
    }

    // funcion que cambia la visibilidsd de la imagen y la quita de GONE para ponerla VSIBLE
    private fun expandirFoto(urlFoto: String) {
        imgGrande.visibility = View.VISIBLE
        Glide.with(this)
            .load(urlFoto)
            .into(imgGrande)
    }

    // Para poner otra vez la visibildiad en GONE y quitar la foto
    private fun contraerFoto() {
        imgGrande.visibility = View.GONE
    }
}