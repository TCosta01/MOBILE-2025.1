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
            var passandoUsername = listaUtilizadores[position].username.toString()
            var passandoPassword = listaUtilizadores[position].password.toString()

            binding.textId.setText("ID: ${passandoID}")
            binding.editUsername.setText(passandoUsername)
            binding.editPassword.setText(passandoPassword)
            pos = position

            var i = Intent(this, PesquisarFormulario::class.java)
            i.putExtra("position",pos)
            i.putExtra("passandoID",passandoID)
            i.putExtra("passandoUsername",passandoUsername)
            i.putExtra("passandoPassword",passandoPassword)
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