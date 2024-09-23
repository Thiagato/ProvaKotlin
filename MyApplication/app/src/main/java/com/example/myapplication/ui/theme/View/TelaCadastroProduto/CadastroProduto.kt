import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.ViewModel.ProdutoViewModel
import com.example.myapplication.ui.theme.Models.Produto
import com.example.myapplication.ui.theme.View.CustomTextField

@Composable
fun CadastroProdutoScreen(navController: NavController, produtoViewModel: ProdutoViewModel) {
    var nomeProduto by remember { mutableStateOf("") }
    var categoriaProduto by remember { mutableStateOf("") }
    var precoProduto by remember { mutableStateOf("") }
    var qtdEstoqueProduto by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Cadastro de Produto")
        CustomTextField(valor = nomeProduto, valorMudar = { nomeProduto = it }, label = "Nome do produto")
        CustomTextField(valor = categoriaProduto, valorMudar = { categoriaProduto = it }, label = "Categoria do produto")
        CustomTextField(valor = precoProduto, valorMudar = { precoProduto = it }, label = "Preço do produto")
        CustomTextField(valor = qtdEstoqueProduto, valorMudar = { qtdEstoqueProduto = it }, label = "Quantidade em estoque")

        Button(onClick = {
            val produto = Produto(nomeProduto, categoriaProduto, precoProduto.toFloat(), qtdEstoqueProduto.toInt())
            produtoViewModel.adicionarProduto(produto)

            nomeProduto = ""
            categoriaProduto = ""
            precoProduto = ""
            qtdEstoqueProduto = ""

            navController.navigate("listaProdutos")
        }) {
            Text(text = "Cadastrar Produto")
        }
    }
}

@Composable
fun ListaProdutosScreen(navController: NavController, produtoViewModel: ProdutoViewModel) {
    LazyColumn {
        items(produtoViewModel.listaProdutos) { produto ->
            Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "${produto.nomeProduto} - ${produto.categoriaProduto} - R$${produto.precoProduto} (Qtd: ${produto.qtdEstoqueProduto})",
                    modifier = Modifier.weight(1f)
                )
                Button(onClick = {
                    navController.navigate("detalhesProduto/${produto.nomeProduto}")
                }) {
                    Text(text = "Detalhes")
                }
            }
        }
    }
}

@Composable
fun DetalhesProdutoScreen(produto: Produto) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Detalhes do Produto", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Nome: ${produto.nomeProduto}")
        Text(text = "Categoria: ${produto.categoriaProduto}")
        Text(text = "Preço: R$ ${produto.precoProduto}")
        Text(text = "Quantidade em Estoque: ${produto.qtdEstoqueProduto}")
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCadastroProdutoScreen() {
    val navController = rememberNavController()
    val produtoViewModel = ProdutoViewModel()

    CadastroProdutoScreen(navController, produtoViewModel)
}
