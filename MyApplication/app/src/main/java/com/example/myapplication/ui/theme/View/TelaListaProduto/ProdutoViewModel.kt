// ProdutoViewModel.kt
package com.example.myapplication.ui.theme.ViewModel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.myapplication.ui.theme.Models.Produto

class ProdutoViewModel : ViewModel() {
    var listaProdutos = mutableStateListOf<Produto>()

    fun adicionarProduto(produto: Produto) {
        listaProdutos.add(produto)
    }
}
