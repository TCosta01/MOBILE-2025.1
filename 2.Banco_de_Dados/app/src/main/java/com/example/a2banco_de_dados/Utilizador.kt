package com.example.a2banco_de_dados

class Utilizador(val id: Int = 0, var nome: String = "", var perfil: String = "", var numero: String = "", var endereco: String = "", var descricao: String = "", var valor: String = "", var ano: String = "", var mes: String = "", var dia: String = "") {


    override fun toString(): String {
        return "Evento(id=$id, cliente='$nome', data='$ano $mes $dia', numero = '$numero', endereco = '$endereco' descricao = '$descricao', valor = '$valor')"
    }
}