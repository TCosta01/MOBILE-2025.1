package com.example.bancodedados

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bancodedados.databinding.ActivityFormularioPreencherBinding

class FormularioPreencher : AppCompatActivity() {

    private lateinit var binding: ActivityFormularioPreencherBinding
    private lateinit var adapter: ArrayAdapter<Utilizador>
    private var pos: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFormularioPreencherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = DBHelper(this)
        val listaUtilizadores = db.utilizadorListSelectAll()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUtilizadores)

        binding.buttonReservar.setOnClickListener {
            val username = binding.editUsername.text.toString()
            val password = binding.editPassword.text.toString()

            val res = db.utilizadorInsert(username, password)

            if (res > 0) {
                Toast.makeText(applicationContext, "Insert OK: $res", Toast.LENGTH_SHORT).show()
                listaUtilizadores.add(Utilizador(res.toInt(), username, password))
                adapter.notifyDataSetChanged()
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(applicationContext, "Insert Erro: $res", Toast.LENGTH_SHORT).show()
            }
        }
    }
}