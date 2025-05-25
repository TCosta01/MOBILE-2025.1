package com.example.agenda

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.agenda.databinding.ActivityCalendarioBinding
import com.example.agenda.databinding.ActivityFormularioPreencherBinding

class FormularioPreencher : AppCompatActivity() {

    private lateinit var binding: ActivityFormularioPreencherBinding

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

        val n = intent
       // Midanças feitas <<<
            /* Tiver que mudar esta parte pelas mudancas da classe "MainActivity", Minhas alterções Parte 01
            val dia = n.getIntExtra("DIA", 0)
            val mes = n.getIntExtra("MES", 0)
            val ano = n.getIntExtra("ANO", 0)
            */
        val dia = n.extras?.getString("DIA")
        val mes = n.extras?.getString("MES")
        val ano = n.extras?.getString("ANO")
        var month:String = mes.toString()
        // >>> Midanças feitas
        /*
        val day = dia.toString()
        val month = mes.toString()
        val year = ano.toString()

         */

        if (mes.equals("1")) { month = "Janeiro"}
        if (mes.equals("2")) { month = "Fevereiro"}
        if (mes.equals("3")) { month = "Março"}
        if (mes.equals("4")) { month = "Abril"}
        if (mes.equals("5")) { month = "Maio"}
        if (mes.equals("6")) { month = "Junho"}
        if (mes.equals("7")) { month = "Julho"}
        if (mes.equals("8")) { month = "Agosto"}
        if (mes.equals("9")) { month = "Setembro"}
        if (mes.equals("10")) { month = "Outubro"}
        if (mes.equals("11")) { month = "Novembro"}
        if (mes.equals("12")) { month = "Dezembro"}




        //binding.textDiaMesAno.setText("$day do $month do $year")
        binding.textDiaMesAno.setText("$dia de $month de $ano")  // 19 de Dezem de 2025

        binding.buttonReservar.setOnClickListener {
         /*
            val  cliente = binding.editCliente.text.toString()
            val numero = binding.editNumero.text.toString()
            val endereco = binding.editEndereco.text.toString()
            val descricao = binding.editDescricao.text.toString()
            val valor = binding.editValor.text.toString()
            */

            var i = Intent(this, LerFormulario::class.java)

            i.putExtra("ano",ano)
            i.putExtra("mes",month)
            i.putExtra("dia",dia)
            i.putExtra("cliente",binding.editCliente.text.toString())
            i.putExtra("numero",binding.editNumero.text.toString())
            i.putExtra("endereco",binding.editEndereco.text.toString())
            i.putExtra("descricao",binding.editDescricao.text.toString())
            i.putExtra("valor",binding.editValor.text.toString())


            startActivity(i)
        }
    }
}