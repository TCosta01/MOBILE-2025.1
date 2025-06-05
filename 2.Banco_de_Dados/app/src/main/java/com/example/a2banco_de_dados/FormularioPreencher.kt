package com.example.a2banco_de_dados

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a2banco_de_dados.databinding.ActivityFormularioPreencherBinding

class FormularioPreencher : AppCompatActivity() {

    private lateinit var binding: ActivityFormularioPreencherBinding
    private lateinit var adapter: ArrayAdapter<Utilizador>
    private var pos: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_formulario_preencher)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val db = DBHelper(this)
        val listaUtilizadores = db.utilizadorListSelectAll()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUtilizadores)


        binding.buttonReservar.setOnClickListener {


            val nome = binding.editCliente.text.toString()
            val numero = binding.editNumero.text.toString()
            val endereco = binding.editEndereco.text.toString()
            val descricao = binding.editDescricao.text.toString()
            val valor = binding.editValor.text.toString()
            val perfil = ""
            var dia = ""
            var mes = ""
            var ano = ""

            val res = db.utilizadorInsert(nome, perfil, numero, endereco, descricao, valor, ano, mes, dia)

            if (res > 0) {
                Toast.makeText(applicationContext, "Insert OK: $res", Toast.LENGTH_SHORT).show()
                listaUtilizadores.add(Utilizador(res.toInt(), nome, perfil, numero, endereco, descricao, valor, ano, mes, dia))
                adapter.notifyDataSetChanged()
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(applicationContext, "Insert Erro: $res", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonEnventos.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}