package com.example.myapplication.ui.theme.Models

class Estoque {
    companion object {
        private val listaProdutos = mutableListOf<Produto>()

        fun adicionarProduto(produto: Produto) {
            listaProdutos.add(produto)
        }

        fun calcularValorTotalEstoque(): Float {
            return listaProdutos.sumOf { (it.precoProduto * it.qtdEstoqueProduto).toDouble() }.toFloat()
        }

        fun calcularQuantidadeTotalProdutos(): Int {
            return listaProdutos.sumOf { it.qtdEstoqueProduto }
        }

        fun obterProdutos(): List<Produto> {
            return listaProdutos
        }
    }
}




