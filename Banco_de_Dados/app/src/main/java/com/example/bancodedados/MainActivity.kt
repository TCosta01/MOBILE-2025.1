package com.example.bancodedados

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bancodedados.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ArrayAdapter<Utilizador>
    private var pos: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = DBHelper(this)

        val listaUtilizadores = db.utilizadorListSelectAll()

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUtilizadores)
        binding.listView.adapter = adapter
        adapter.notifyDataSetChanged()

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            /*
            binding.textId.setText("ID: ${listaUtilizadores[position].id}")
            binding.editUsername.setText(listaUtilizadores[position].username)
            binding.editPassword.setText(listaUtilizadores[position].password)
            pos = position
            */
            var passandoID = listaUtilizadores[position].id.toString()


            var passandoNome = listaUtilizadores[position].nome.toString()
            var passandoPerfil = listaUtilizadores[position].perfil.toString()
            var passandoNumero = listaUtilizadores[position].numero.toString()
            var passandoEnderaco = listaUtilizadores[position].endereco.toString()
            var passandoDescricao = listaUtilizadores[position].descricao.toString()
            var passandoValor = listaUtilizadores[position].valor.toString()
            var passandoAno = listaUtilizadores[position].ano.toString()
            var passandoMes = listaUtilizadores[position].mes.toString()
            var passandoDia = listaUtilizadores[position].dia.toString()


            pos = position

            var i = Intent(this, PesquisarFormulario::class.java)
            i.putExtra("position",pos)
            i.putExtra("passandoID",passandoID)
            i.putExtra("passandoNome",passandoNome)
            i.putExtra("passandoPerfil",passandoPerfil)
            i.putExtra("passandoNumero",passandoNumero)
            i.putExtra("passandoEnderaco",passandoEnderaco)
            i.putExtra("passandoDescricao",passandoDescricao)
            i.putExtra("passandoValor",passandoValor)
            i.putExtra("passandoAno",passandoAno)
            i.putExtra("passandoMes",passandoMes)
            i.putExtra("passandoDia",passandoDia)
            startActivity(i)
        }









        /*
        binding.buttonUpdate.setOnClickListener {
            if (pos > 0) {
                val id = listaUtilizadores[pos].id
                val username = binding.editUsername.text.toString()
                val password = binding.editPassword.text.toString()

                val res = db.utilizadorUpdate(id, username, password)
                if (res > 0) {
                    Toast.makeText(applicationContext, "Update OK: $res", Toast.LENGTH_SHORT).show()
                    listaUtilizadores[pos].username = username
                    listaUtilizadores[pos].password = password
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(applicationContext, "Update Erro: $res", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        } */
    }
}