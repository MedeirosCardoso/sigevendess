package teste;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sigevendees.dao.PedidoDao;
import br.com.sigevendees.dao.VendaDao;
import br.com.sigevendees.entity.Cliente;
import br.com.sigevendees.entity.Pedido;
import br.com.sigevendees.entity.Produto;
import br.com.sigevendees.entity.Telefone;
import br.com.sigevendees.entity.Venda;
import br.com.sigevendees.enums.CategoryTypes;
import br.com.sigevendees.enums.MeasureUnits;
import br.com.sigevendees.enums.PaymentOptions;
import br.com.sigevendees.enums.Status;

public class TestaVenda {
	static VendaDao daoVenda = new VendaDao();
	static PedidoDao daoPedido = new PedidoDao();

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

		Venda venda = new Venda(PaymentOptions.DINHEIRO, new Date());
		venda.addPedido(pedido);
		// Altera o status do pedido.
		pedido.setSituacao(Status.VENDIDO);
		// Mostra a venda.
		System.out.println(venda);

	}

	public static void testaSalvar() {
		// Busca os pedidos.
		List<Pedido> pedidos = new ArrayList<>();
		pedidos.add(daoPedido.buscarPor(14));
		pedidos.add(daoPedido.buscarPor(15));
		// cria uma venda.
		Venda venda = new Venda(PaymentOptions.CREDITO, new Date());
		// Adiciona os pedidos da Venda.
		for (Pedido pedidoVenda : pedidos) {
			venda.addPedido(pedidoVenda);
		}
		// Salvar Venda.
		if (daoVenda.salvar(venda)) {
			System.out.println("Venda salvo com sucesso!");
		} else {
			System.out.println("Não foi possivel salvar venda!");
		}

	}

	public static void testaBuscarPorId(Integer cod) {
		Venda resultado = daoVenda.buscarPor(cod);
		if (resultado != null) {
			System.out.println(resultado);
		} else {
			System.out.print("Venda não realizada!");
		}

	}

	public static void testaBuscarTodos() {
		for (Venda venda : daoVenda.buscarTodos()) {
			System.out.println(venda);
			System.out.println();
		}

	}

	public static void main(String[] args) {
		// testaClasse();
		// testaSalvar();
		// testaBuscarPorId(16);
		// testaBuscarTodos();

	}

}
