package com.example.myapplication.ui.theme.Models

class Produto(
    var nomeProduto: String,
    var categoriaProduto: String,
    var precoProduto: Float,
    var qtdEstoqueProduto: Int
) {
    companion object {

        val listaProdutos = mutableListOf<Produto>()
    }
}