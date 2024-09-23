package com.example.myapplication.ui.theme.Controllers

import com.example.myapplication.ui.theme.Models.Produto

class ProdutoController {
    fun cadastrarProduto(
        nome: String,
        categoria: String,
        preco: String,
        quantidade: String
    ): String? {
        if (nome.isEmpty() || categoria.isEmpty() || preco.isEmpty() || quantidade.isEmpty()) {
            return "Todos os campos sao obrigatorio"
        }

        val produto = Produto(nome, categoria, preco.toFloat(), quantidade.toInt())
        Produto.listaProdutos.add(produto)
        return null
    }
}