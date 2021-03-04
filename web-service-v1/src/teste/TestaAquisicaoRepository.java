package teste;

import java.util.ArrayList;
import java.util.List;
import br.com.sigevendees.dao.AquisicaoDao;
import br.com.sigevendees.entity.Aquisicao;
import br.com.sigevendees.entity.ItemAquisicao;
import br.com.sigevendees.repository.AquisicaoRepository;

public class TestaAquisicaoRepository {

	static AquisicaoDao dao = new AquisicaoDao();
	static AquisicaoRepository repo = new AquisicaoRepository();

	public static void testaConverterJavaParaJson_e_vice_versa(Integer cod) {
		// Guarda os valores retornado do BD.
		Aquisicao resultado = dao.buscarPor(cod);
		System.out.println("\nAquisição Retornado");
		System.out.println(resultado);

		System.out.println("\nAquisicao em JSON");
		// Pega os componentes da aquisição retornado.
		List<ItemAquisicao> componentes = new ArrayList<>();
		for (ItemAquisicao item : resultado.getComponentes()) {
			componentes.add(new ItemAquisicao(item.getComponente(), item.getQtdAdquirida(), item.getCusto()));
		}
		// Instancia uma nova Aquisicao com os dados da Aquisicao retornado do BD.
		Aquisicao aquisicaoEmJava = new Aquisicao(resultado.getCodigo(), resultado.getDataAquisicao(), componentes);
		// Converte para JSON.
		String aquisicaoEmJson = repo.converterParaJSON(aquisicaoEmJava);
		System.out.println(aquisicaoEmJson);

		// Converte para Java.
		aquisicaoEmJava = repo.converterParaJava(aquisicaoEmJson);
		System.out.println("\nAquisicao em JAVA");
		System.out.println(aquisicaoEmJava);
	}

	public static void testaConverterListJavaParaJson_e_vice_versa() {
		// Guarda todos as aquisições retornado do BD.
		List<Aquisicao> aquisicoes = dao.buscarTodos();
		System.out.println("\nAquisições Retornado");
		for (Aquisicao aquisicao : aquisicoes) {
			System.out.println(aquisicao);
		}
		System.out.println("\nAquisições em JSON");
		// Pega todos os componentes de cada aquisição retornada.
		// Criar uma nova lista de aquisições com os dados das aquisições retornadas.
		List<Aquisicao> lista = new ArrayList<>();
		for (Aquisicao aquisicao : aquisicoes) {
			List<ItemAquisicao> componentes = new ArrayList<>();
			for (ItemAquisicao item : aquisicao.getComponentes()) {
				componentes.add(new ItemAquisicao(item.getComponente(), item.getQtdAdquirida(), item.getCusto()));
			}
			lista.add(new Aquisicao(aquisicao.getCodigo(), aquisicao.getDataAquisicao(), componentes));
		}
		// Convete para JSON.
		String listEmJson = repo.converterParaArrayJSON(lista);
		System.out.println(listEmJson);

		System.out.println("\nAquisições em JAVA");
		aquisicoes = repo.converterParaListaJava(listEmJson);
		for (Aquisicao aquisicao : aquisicoes) {
			System.out.println(aquisicao);
		}
	}

	public static void testaNovoComponente() {

	}

	public static void main(String[] args) {
		testaConverterJavaParaJson_e_vice_versa(6);
		//testaConverterListJavaParaJson_e_vice_versa();

	}

}
