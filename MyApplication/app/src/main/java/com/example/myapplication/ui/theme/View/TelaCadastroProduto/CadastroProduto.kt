import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.Controller.CustomTextField
import com.example.myapplication.ui.theme.Models.Produto

@Composable
fun CadastroProdutoScreen(navController: NavController, produtoViewModel: ProdutoViewModel) {
    var nomeProduto by remember { mutableStateOf("") }
    var categoriaProduto by remember { mutableStateOf("") }
    var precoProduto by remember { mutableStateOf("") }
    var qtdEstoqueProduto by remember { mutableStateOf("") }
    val context = LocalContext.current

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
            val preco = precoProduto.toFloatOrNull()
            val quantidade = qtdEstoqueProduto.toIntOrNull()

            if (preco == null || quantidade == null || preco < 0 || quantidade < 1) {
                Toast.makeText(context, "Preço tem que ser maior ou igual a 0 e a quantidade deve ser maior ou igual a 1", Toast.LENGTH_LONG).show()
            } else {
                val produto = Produto(nomeProduto, categoriaProduto, preco, quantidade)
                produtoViewModel.adicionarProduto(produto)

                nomeProduto = ""
                categoriaProduto = ""
                precoProduto = ""
                qtdEstoqueProduto = ""

                navController.navigate("listaProdutos")
            }
        }) {
            Text(text = "Cadastrar Produto")
        }
    }
}

@Composable
fun ListaProdutosScreen(navController: NavController, produtoViewModel: ProdutoViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(produtoViewModel.listaProdutos) { produto ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "${produto.nomeProduto} - ${produto.categoriaProduto} - R$${produto.precoProduto} (Qtd: ${produto.qtdEstoqueProduto})"

                    )
                    Button(onClick = {
                        navController.navigate("detalhesProduto/${produto.nomeProduto}")
                    }) {
                        Text(text = "Detalhes")
                    }
                }
            }
        }
        Button(
            onClick = { navController.navigate("estatisticas") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(text = "Ver Estatísticas")
        }
    }
}

@Composable
fun EstatisticasScreen(produtoViewModel: ProdutoViewModel) {
    val valorTotalEstoque = produtoViewModel.calcularValorTotalEstoque()
    val quantidadeTotalProdutos = produtoViewModel.calcularQuantidadeTotalProdutos()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Estatísticas do Estoque",
        )
        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Valor Total do Estoque: R$ $valorTotalEstoque")
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Quantidade Total de Produtos: $quantidadeTotalProdutos")
    }
}

@Composable
fun DetalhesProdutoScreen(produto: Produto, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(15.dp)) {
        Text(text = "Detalhes do Produto")
        Text(text = "Nome: ${produto.nomeProduto}")
        Text(text = "Categoria: ${produto.categoriaProduto}")
        Text(text = "Preço: R$ ${produto.precoProduto}")
        Text(text = "Quantidade em Estoque: ${produto.qtdEstoqueProduto}")

        Spacer(modifier = Modifier.height(10.dp))


        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Voltar")
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewCadastroProdutoScreen() {
    val navController = rememberNavController()
    val produtoViewModel = ProdutoViewModel()

    CadastroProdutoScreen(navController, produtoViewModel)
}
