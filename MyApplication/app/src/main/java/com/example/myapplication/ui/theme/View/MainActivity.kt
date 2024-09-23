package com.example.myapplication.ui.theme.View

import CadastroProdutoScreen
import ListaProdutosScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.View.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.ViewModel.ProdutoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(valor: String,  valorMudar: (String) -> Unit, label: String
) {
    TextField(
        value = valor,
        onValueChange = { novoValor -> valorMudar(novoValor) },
        label = { Text(text = label) }
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}