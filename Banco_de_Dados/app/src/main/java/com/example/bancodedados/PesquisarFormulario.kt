package com.example.bancodedados

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bancodedados.databinding.ActivityPesquisarFormularioBinding

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
        var recebeuUsername = i.extras?.getString("passandoUsername")
        var recebeuPassword = i.extras?.getString("passandoPassword")

        if (position != null) {
            pos = position
        }


        binding.textPosision.setText("Position: ${pos}")
        binding.textId.setText("ID: ${recebeuID}")
        binding.editUsername.setText(recebeuUsername)
        binding.editPassword.setText(recebeuPassword)

        val db = DBHelper(this)
        val listaUtilizadores = db.utilizadorListSelectAll()


        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaUtilizadores)


        binding.buttonUpdate.setOnClickListener {
            if (pos >= 0) {
                val id = listaUtilizadores[pos].id
                val username = binding.editUsername.text.toString()
                val password = binding.editPassword.text.toString()

                val res = db.utilizadorUpdate(id, username, password)

                if (res > 0) {
                    Toast.makeText(applicationContext, "Update OK: $res", Toast.LENGTH_SHORT).show()
                    listaUtilizadores[pos].username = username
                    listaUtilizadores[pos].password = password
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