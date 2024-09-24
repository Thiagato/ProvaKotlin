import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.theme.Models.Estoque
import com.example.myapplication.ui.theme.Models.Produto

class ProdutoViewModel : ViewModel() {

    val listaProdutos: List<Produto>
        get() = Estoque.obterProdutos()

    fun adicionarProduto(produto: Produto) {
        Estoque.adicionarProduto(produto)
    }

    fun calcularValorTotalEstoque(): Float {
        return Estoque.calcularValorTotalEstoque()
    }

    fun calcularQuantidadeTotalProdutos(): Int {
        return Estoque.calcularQuantidadeTotalProdutos()
    }
}
