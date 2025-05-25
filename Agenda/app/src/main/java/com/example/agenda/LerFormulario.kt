package com.example.agenda

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.agenda.databinding.ActivityLerFormularioBinding
import com.example.agenda.databinding.ActivityPesquisaBinding

class LerFormulario : AppCompatActivity() {

    private lateinit var binding: ActivityLerFormularioBinding

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLerFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val i = intent
        val ano = i.extras?.getString("ano")
        val mes = i.extras?.getString("mes")
        val dia = i.extras?.getString("dia")
        val cliente = i.extras?.getString("cliente")
        val numero = i.extras?.getString("numero")
        val endereco = i.extras?.getString("endereco")
        val descricao = i.extras?.getString("descricao")
        val valor = i.extras?.getString("valor")






        if (ano.equals("") || ano == null || mes.equals("") || mes == null || dia.equals("") || dia == null) {
            binding.textLerDiaMesAno.setText("Data não informada")
        }   else {
            binding.textLerDiaMesAno.setText("${dia} de ${mes} de ${ano}")
        }

        if (cliente.equals("") || cliente == null) {
            binding.textNome.setText("Não preenchido")
        }   else {
            binding.textNome.setText("$cliente")
        }

        if (numero.equals("") || numero == null) {
            binding.textNumero.setText("Não preenchido")
        }   else {
            binding.textNumero.setText("$numero")
        }

        if (endereco.equals("") || endereco == null) {
            binding.textEndereco.setText("Não preenchido")
        }   else {
            binding.textEndereco.setText("$endereco")
        }

        if (descricao.equals("") || descricao == null) {
            binding.textDescricao.setText("Não preenchido")
        }   else {
            binding.textDescricao.setText("$descricao")
        }

        if (valor.equals("") || valor == null) {
            binding.textValor.setText("Não preenchido")
        }   else {
            binding.textValor.setText("$valor")
        }


        binding.buttonCasa.setOnClickListener {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

    }
}