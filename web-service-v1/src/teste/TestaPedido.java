package teste;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sigevendees.dao.ClienteDao;
import br.com.sigevendees.dao.PedidoDao;
import br.com.sigevendees.dao.ProdutoDao;
import br.com.sigevendees.entity.Cliente;
import br.com.sigevendees.entity.Pedido;
import br.com.sigevendees.entity.Produto;
import br.com.sigevendees.entity.Telefone;
import br.com.sigevendees.enums.CategoryTypes;
import br.com.sigevendees.enums.MeasureUnits;
import br.com.sigevendees.enums.Status;

public class TestaPedido {
	static PedidoDao daoPedido = new PedidoDao();
	static ProdutoDao daoProduto = new ProdutoDao();
	static ClienteDao daoCliente = new ClienteDao();

	public static void testaClasse() {
		// Busca o cliente do Pedido.
		Telefone telefone = new Telefone((byte) 47, 999999999);
		String nome = "CLIENTE TESTE";
		Cliente cliente = new Cliente();
		cliente.setNumero(telefone);
		cliente.setNome(nome);

		// Busca os produtos do Pedido.
		Produto bolo = new Produto(null, "Bolo", CategoryTypes.DOCE, MeasureUnits.UNIDADE, 7);
		Produto sanduiche = new Produto(null, "Sanduíche", CategoryTypes.SALGADO, MeasureUnits.UNIDADE, 7);
		Produto sobremesa = new Produto(null, "Pudim", CategoryTypes.DOCE, MeasureUnits.UNIDADE, 7);

		// Cria o pedido e adiciona o produto a sua lista de produtos.
		Pedido pedido = new Pedido(cliente, new Date());
		pedido.addItem(bolo, 5, 0);
		pedido.addItem(sanduiche, 2, 1);
		pedido.addItem(sobremesa, 1, 0);
		pedido.setDataProduzido(new Date());
		// Mostra o pedido.
		System.out.println(pedido);

	}

	public static void testaSalvar() {
		// Busca o cliente do Pedido.
		Telefone telefone = new Telefone((byte) 47, 999999999);
		Cliente cliente = daoCliente.buscarPor(telefone);

		// Busca os produtos do Pedido.
		Produto bolo = daoProduto.buscarPor(4);
		Produto hotDog = daoProduto.buscarPor(12);

		// Cria o pedido e adiciona o produto a sua lista de produtos.
		Pedido pedido = new Pedido(cliente, new Date());
		pedido.addItem(bolo, 5, 0);
		pedido.addItem(hotDog, 1, 0);

		// Salvar pedido.
		if (daoPedido.salvar(pedido)) {
			System.out.println("Pedido salvo com sucesso!");
		} else {
			System.out.println("Não foi possivel salvar pedido!");
		}

	}

	public static void testaBuscarPorId(Integer cod) {
		Pedido resultado = daoPedido.buscarPor(cod);
		if (resultado != null) {
			System.out.println(resultado);
		} else {
			System.out.print("Pedido não cadastrado!");
		}

	}

	public static void testaBuscarTodos() {
		for (Pedido pedido : daoPedido.buscarTodos()) {
			System.out.println(pedido);
			System.out.println();
		}

	}

	public static void testaAtualizar() {
		List<Pedido> pedidos = new ArrayList<>();
		pedidos.add(daoPedido.buscarPor(14));
		pedidos.add(daoPedido.buscarPor(15));
		for (Pedido pedido : pedidos) {
			// Pedidos com as inforamçõs a ser alterado.
			System.out.println(pedido);
			// Informa a data que foi produzido os produtos do Pedido.
			pedido.setDataProduzido(new Date());
			// Altera o status do Pedido.
			pedido.setSituacao(Status.PRONTO);
			// Salva as alterações.
			daoPedido.atualizar(pedido);
			// Pedido com as informações atualizada.
			System.out.println(pedido);
		}

	}

	public static void main(String[] args) {
		// testaClasse();
		// testaSalvar();
		// testaBuscarPorId(15);
		// testaBuscarTodos();
		// testaAtualizar();
	}

}
