package com.example.a2banco_de_dados

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a2banco_de_dados.databinding.ActivityPesquisarFormularioBinding

class PesquisarFormulario : AppCompatActivity() {
    private lateinit var binding: ActivityPesquisarFormularioBinding
    private lateinit var adapter: ArrayAdapter<Utilizador>
    private var pos: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPesquisarFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val i = intent
        var position = i.extras?.getInt(("position"))
        var recebeuID = i.extras?.getString("passandoID")
        var passandoNome = i.extras?.getString("passandoNome")
        var passandoPerfil = i.extras?.getString("passandoPerfil")
        var passandoNumero = i.extras?.getString("passandoNumero")
        var passandoEnderaco = i.extras?.getString("passandoEnderaco")
        var passandoDescricao = i.extras?.getString("passandoDescricao")
        var passandoValor = i.extras?.getString("passandoValor")
        var passandoAno = i.extras?.getString("passandoAno")
        var passandoMes = i.extras?.getString("passandoMes")
        var passandoDia = i.extras?.getString("passandoDia")

        var per = passandoPerfil.toString()
        var di = passandoDia.toString()
        var me = passandoMes.toString()
        var an = passandoAno.toString()


        if (position != null) {
            pos = position
        }



        binding.editCliente.setText(passandoNome)
        binding.editNumero.setText(passandoNumero)
        binding.editEndereco.setText(passandoEnderaco)
        binding.editDescricao.setText(passandoDescricao)
        binding.editValor.setText(passandoValor)
        binding.textData.setText("${passandoDia} do ${passandoMes} de ${passandoAno}")

        val db = DBHelper(this)
        val listaUtilizadores = db.utilizadorListSelectAll()


        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUtilizadores)


        binding.buttonUpdate.setOnClickListener {
            if (pos >= 0) {
                val id = listaUtilizadores[pos].id
                val nome = binding.editCliente.text.toString()
                val perfil: String = per
                val numero = binding.editNumero.text.toString()
                val endereco = binding.editEndereco.text.toString()
                val descricao = binding.editDescricao.text.toString()
                val valor = binding.editValor.text.toString()
                val ano: String = an
                val mes: String = me
                val dia: String = di





                val res = db.utilizadorUpdate(id, nome, perfil, numero, endereco, descricao, valor, ano, mes, dia)

                if (res > 0) {
                    Toast.makeText(applicationContext, "Update OK: $res", Toast.LENGTH_SHORT).show()


                    listaUtilizadores[pos].nome = nome
                    listaUtilizadores[pos].perfil = perfil
                    listaUtilizadores[pos].numero = numero
                    listaUtilizadores[pos].endereco = endereco
                    listaUtilizadores[pos].descricao = descricao
                    listaUtilizadores[pos].valor = valor
                    listaUtilizadores[pos].ano = ano
                    listaUtilizadores[pos].mes = mes
                    listaUtilizadores[pos].dia = dia
                    adapter.notifyDataSetChanged()
                    startActivity(Intent(this, MainActivity::class.java))

                } else {
                    Toast.makeText(applicationContext, "Update Erro: $res", Toast.LENGTH_SHORT)
                        .show()

                }

            }
        }

        binding.buttonDelete.setOnClickListener{
            if(pos>=0){
                val id = listaUtilizadores[pos].id
                var res = db.utilizadorDelete(id)

                if (res > 0) {
                    Toast.makeText(applicationContext, "Delete OK: $res", Toast.LENGTH_SHORT).show()
                    listaUtilizadores.removeAt(pos)
                    adapter.notifyDataSetChanged()
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(applicationContext, "Delete Erro: $res", Toast.LENGTH_SHORT)
                        .show()

                }
            }
        }

    }
}