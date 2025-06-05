package com.example.a2banco_de_dados

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, "agenda.db", null, 1) {

    val sql = arrayOf(
        /*
        "CREATE TABLE utilizador (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)",
        "INSERT INTO utilizador (username, password) VALUES ('user','pass');",
        "INSERT INTO utilizador (username, password) VALUES ('admin','pwd');"
         */
        "CREATE TABLE clientes (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, perfil TEXT, numero TEXT, endereco TEXT, descricao TEXT, valor TEXT, ano TEXT, mes TEXT, dia TEXT)",
        "INSERT INTO utilizador (nome, perfil, numero, endereco, descricao, valor, ano, mes, dia) VALUES ('bob','traquilo','82999','maceio','bar','900','2026','5','30');",
        "INSERT INTO utilizador (nome, perfil, numero, endereco, descricao, valor, ano, mes, dia) VALUES ('tiago','nao traquilo','6800099','jacarecica','piscina','1000','2024','5','15');",
        "INSERT INTO utilizador (nome, perfil, numero, endereco, descricao, valor, ano, mes, dia) VALUES ('jack','traquilo','8500','serraria','barraco','60','2004','7','25');",
        "INSERT INTO utilizador (nome, perfil, numero, endereco, descricao, valor, ano, mes, dia) VALUES ('valeria','nao traquilo','9900','jacarecica','Pula-Pula','60','2004','5','25');",
        "INSERT INTO utilizador (nome, perfil, numero, endereco, descricao, valor, ano, mes, dia) VALUES ('noemi','traquilo','6800099','maceio','barraco','1000','2024','5','15');",
        "INSERT INTO utilizador (nome, perfil, numero, endereco, descricao, valor, ano, mes, dia) VALUES ('neli','nao traquilo','8500','maceio','piscina','900','2024','7',30');",
        "INSERT INTO utilizador (nome, perfil, numero, endereco, descricao, valor, ano, mes, dia) VALUES ('neila','nao traquilo','82999','serraria','Pula-Pula','60','2026','12','30');",
        "INSERT INTO utilizador (nome, perfil, numero, endereco, descricao, valor, ano, mes, dia) VALUES ('tacio','traquilo','82999','barraco','piscina','900','2026','12','10');"

    )

    override fun onCreate(db: SQLiteDatabase) {
        sql.forEach {
            db.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE clientes")
        onCreate(db)
    }

    // fun utilizadorInsert(username: String, password: String): Long {
    fun utilizadorInsert(nome: String, perfil: String, numero: String, endereco: String, descricao: String, valor: String, ano: String, mes: String, dia: String): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        /*
        contentValues.put("username", username)
        contentValues.put("password", password)
        */

        contentValues.put("nome", nome)
        contentValues.put("perfil", perfil)
        contentValues.put("numero", numero)
        contentValues.put("endereco", endereco)
        contentValues.put("descricao", descricao)
        contentValues.put("valor", valor)
        contentValues.put("ano", ano)
        contentValues.put("mes", mes)
        contentValues.put("dia", dia)


        val res = db.insert("clientes", null, contentValues)
        db.close()
        return res
    }

    fun utilizadorUpdate(id: Int, nome: String, perfil: String, numero: String, endereco: String, descricao: String, valor: String, ano: String, mes: String, dia: String): Int {
        //fun utilizadorUpdate(id: Int, username: String, password: String): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        /*
        contentValues.put("username", username)
        contentValues.put("password", password)
        */
        contentValues.put("nome", nome)
        contentValues.put("perfil", perfil)
        contentValues.put("numero", numero)
        contentValues.put("endereco", endereco)
        contentValues.put("descricao", descricao)
        contentValues.put("valor", valor)
        contentValues.put("ano", ano)
        contentValues.put("mes", mes)
        contentValues.put("dia", dia)


        val res = db.update("clientes", contentValues, "id=?", arrayOf(id.toString()))
        // val res = db.update("utilizador", contentValues, "id=?", arrayOf(id.toString()))

        db.close()
        return res
    }

    fun utilizadorDelete(id: Int): Int {
        val db = this.writableDatabase

        val res = db.delete("clientes", "id=?", arrayOf(id.toString()))
        //val res = db.delete("utilizador", "id=?", arrayOf(id.toString()))

        db.close()
        return res
    }

    fun utilizadorSelectAll(): Cursor {
        val db = this.readableDatabase

        val c = db.rawQuery("SELECT * FROM clientes", null)
        //val c = db.rawQuery("SELECT * FROM utilizador", null)

        db.close()
        return c
    }

    fun utilizadorListSelectAll(): ArrayList<Utilizador> {
        val db = this.readableDatabase

        val c = db.rawQuery("SELECT * FROM clientes", null)
        //val c = db.rawQuery("SELECT * FROM utilizador", null)


        val listaUtilizador: ArrayList<Utilizador> = ArrayList()
        if (c.count > 0) {
            c.moveToFirst()
            do {
                val idIndex = c.getColumnIndex("id")

                /*
               val usernameIndex = c.getColumnIndex("username")
               val passwordIndex = c.getColumnIndex("password")
               */
                val nomeIndex = c.getColumnIndex("nome")
                val perfilIndex = c.getColumnIndex("perfil")
                val numeroIndex = c.getColumnIndex("numero")
                val enderecoIndex = c.getColumnIndex("endereco")
                val descricaoIndex = c.getColumnIndex("descricao")
                val valorIndex = c.getColumnIndex("valor")
                val anoIndex = c.getColumnIndex("ano")
                val mesIndex = c.getColumnIndex("mes")
                val diaIndex = c.getColumnIndex("dia")


                val id = c.getInt(idIndex)
                /*
               val username = c.getString(usernameIndex)
               val password = c.getString(passwordIndex)
               */
                val nome = c.getString(nomeIndex)
                val perfil = c.getString(perfilIndex)
                val numero = c.getString(numeroIndex)
                val endereco = c.getString(enderecoIndex)
                val descricao = c.getString(descricaoIndex)
                val valor = c.getString(valorIndex)
                val ano = c.getString(anoIndex)
                val mes = c.getString(mesIndex)
                val dia = c.getString(diaIndex)



                listaUtilizador.add(Utilizador(id, nome, perfil, numero, endereco, descricao, valor, ano, mes, dia))
                // listaUtilizador.add(Utilizador(id, username, password))
            } while (c.moveToNext())
        }
        db.close()
        return listaUtilizador
    }

    fun utilizadorSelectByID(id: Int): Cursor {
        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM clientes WHERE id=?", arrayOf(id.toString()))
        // return db.rawQuery("SELECT * FROM utilizador WHERE id=?", arrayOf(id.toString()))
    }

    fun utilizadorObjectSelectByID(id: Int): Utilizador {
        val db = this.readableDatabase

        val c = db.rawQuery("SELECT * FROM clientes WHERE id=?", arrayOf(id.toString()))
        //val c = db.rawQuery("SELECT * FROM utilizador WHERE id=?", arrayOf(id.toString()))

        var utilizador = Utilizador()

        if (c.count == 1) {
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")

            /*
           val usernameIndex = c.getColumnIndex("username")
           val passwordIndex = c.getColumnIndex("password")
           */
            val nomeIndex = c.getColumnIndex("nome")
            val perfilIndex = c.getColumnIndex("perfil")
            val numeroIndex = c.getColumnIndex("numero")
            val enderecoIndex = c.getColumnIndex("endereco")
            val descricaoIndex = c.getColumnIndex("descricao")
            val valorIndex = c.getColumnIndex("valor")
            val anoIndex = c.getColumnIndex("ano")
            val mesIndex = c.getColumnIndex("mes")
            val diaIndex = c.getColumnIndex("dia")


            val id = c.getInt(idIndex)
            /*
           val username = c.getString(usernameIndex)
           val password = c.getString(passwordIndex)
           */
            val nome = c.getString(nomeIndex)
            val perfil = c.getString(perfilIndex)
            val numero = c.getString(numeroIndex)
            val endereco = c.getString(enderecoIndex)
            val descricao = c.getString(descricaoIndex)
            val valor = c.getString(valorIndex)
            val ano = c.getString(anoIndex)
            val mes = c.getString(mesIndex)
            val dia = c.getString(diaIndex)



            utilizador = Utilizador(id, nome, perfil, numero, endereco, descricao, valor, ano, mes, dia)

        }
        return utilizador
    }

}