package com.example.agenda

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.agenda.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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

        binding.buttonJaneiro.setOnClickListener {
            var m = Calendar.JANUARY
            mostrarDatePickerDialog(m)
        }
        binding.buttonFevereiro.setOnClickListener {
            var m = Calendar.FEBRUARY
            mostrarDatePickerDialog(m)
        }
        binding.buttonMarco.setOnClickListener {
            var m = Calendar.MARCH
            mostrarDatePickerDialog(m)
        }
        binding.buttonAbril.setOnClickListener {
            var m = Calendar.APRIL
            mostrarDatePickerDialog(m)
        }
        binding.buttonMaio.setOnClickListener {
            var m = Calendar.MAY
            mostrarDatePickerDialog(m)
        }
        binding.buttonJunho.setOnClickListener {
            var m = Calendar.JUNE
            mostrarDatePickerDialog(m)
        }
        binding.buttonJulho.setOnClickListener {
            var m = Calendar.JULY
            mostrarDatePickerDialog(m)
        }
        binding.buttonAgosto.setOnClickListener {
            var m = Calendar.AUGUST
            mostrarDatePickerDialog(m)
        }
        binding.buttonSetembro.setOnClickListener {
            var m = Calendar.SEPTEMBER
            mostrarDatePickerDialog(m)
        }
        binding.buttonOutubro.setOnClickListener {
            var m = Calendar.OCTOBER
            mostrarDatePickerDialog(m)
        }
        binding.buttonNovembro.setOnClickListener {
            var m = Calendar.NOVEMBER
            mostrarDatePickerDialog(m)
        }
        binding.buttonDezembro.setOnClickListener {
            var m = Calendar.DECEMBER
            mostrarDatePickerDialog(m)
        }
    }


    private fun mostrarDatePickerDialog(m: Int) {
        // *** Novas variáveis para o ano e mês predefinidos ***
        val anoPredefinido = 2025 // Defina o ano que você deseja
        val mesPredefinido = m // Defina o mês que você deseja (Calendar.MONTH é baseado em 0, então Calendar.JULY é 6)
        val diaPadrao = 1 // Dia padrão para a abertura (pode ser qualquer dia do mês predefinido)

        // Se você quiser que ele abra na data atual, com o ano/mês predefinidos,
        // use o dia atual em vez de um dia padrão.
        // val calendarioAtual = Calendar.getInstance()
        // val diaAtual = calendarioAtual.get(Calendar.DAY_OF_MONTH)


        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                // O mês retornado é de 0 a 11, então adicione 1 para o mês real
                val mesReal = selectedMonth + 1
                /*val dataSelecionada = "$selectedDayOfMonth/$mesReal/$selectedYear"
                textViewDataSelecionada.text = "Data selecionada: $dataSelecionada"*/

                // Adicionado por Mim Parte 01 - Minhas alterções Parte 01 <<<
                    var day = selectedDayOfMonth.toString()
                    var month = mesReal.toString()
                    var year = selectedYear.toString()
                // >>> Adicionado por Mim Parte 01 - Minhas alterções Parte 01

                // Passar a data para a próxima tela
                val intent = Intent(this, FormularioPreencher::class.java).apply {
                    // Minhas alterções Parte 01 <<<
                        /* Parte do codigo original
                        putExtra("DIA", selectedDayOfMonth)
                        putExtra("MES", mesReal)
                        putExtra("ANO", selectedYear)
                         */
                        putExtra("DIA", day)
                        putExtra("MES", month)
                        putExtra("ANO", year)
                    // >>> Minhas alterções Parte 01
                }
                startActivity(intent)
            },
            anoPredefinido, // Usamos o ano predefinido aqui
            mesPredefinido, // Usamos o mês predefinido aqui
            diaPadrao // Usamos o dia padrão aqui
        )
        datePickerDialog.show()
    }


}