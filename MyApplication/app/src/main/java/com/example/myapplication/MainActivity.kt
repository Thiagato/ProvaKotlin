package com.example.myapplication

import CadastroProdutoScreen
import DetalhesProdutoScreen
import EstatisticasScreen
import ListaProdutosScreen
import ProdutoViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                val produtoViewModel: ProdutoViewModel = viewModel()

                NavHost(navController, startDestination = "cadastroProduto") {
                    composable("cadastroProduto") {
                        CadastroProdutoScreen(
                            navController,
                            produtoViewModel
                        )
                    }
                    composable("listaProdutos") {
                        ListaProdutosScreen(
                            navController,
                            produtoViewModel
                        )
                    }
                    composable("detalhesProduto/{nomeProduto}") { backStackEntry ->
                        val nomeProduto = backStackEntry.arguments?.getString("nomeProduto") ?: ""
                        val produto = produtoViewModel.listaProdutos.find { it.nomeProduto == nomeProduto }
                        if (produto != null) {
                            DetalhesProdutoScreen(produto, navController)
                        }
                    }

                    composable("estatisticas") {
                        EstatisticasScreen(
                            produtoViewModel
                        )
                    }

                }
            }
        }
}
}