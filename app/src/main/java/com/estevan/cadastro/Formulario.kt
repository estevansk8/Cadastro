package com.estevan.cadastro

class Formulario {
    var city: String  = ""
    var phone: String = ""
    var name: String  = ""
    var email: String = ""
    var state: String = ""
    var sexo: String = ""
    var check: String = ""

    override fun toString(): String {
        return "Nome: " + name +
                "\nEmail: " + email +
                "\nTelefone: " + phone +
                "\nCidade: " + city +
                "\nEstado: " + state +
                "\nSexo: " + sexo +
                "\nLista de Emails: " + check

    }
}