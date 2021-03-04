package br.com.sigevendees.repository;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import br.com.sigevendees.dao.AquisicaoDao;
import br.com.sigevendees.entity.Aquisicao;
import br.com.sigevendees.entity.ItemAquisicao;

public class AquisicaoRepository extends GenericRepository<Aquisicao, Integer> {

	public AquisicaoRepository() {
		setDao(new AquisicaoDao());
		setClasse(Aquisicao.class);
		// Configura o Gson para serializar objetos Date de acordo com o padrão fornecido.
		setGson(new GsonBuilder().setDateFormat("dd/MM/yyyy").create());
		setCollectionType(new TypeToken<List<Aquisicao>>() {}.getType());
	}

	@Override
	public Response novo(String dadosEmJson) {
		// Pega dos dados em JSON vindo do front.
		// Converter para objeto java do tipo Aquisicao.
		Aquisicao dadosNovaAquisicao = converterParaJava(dadosEmJson);
		if (dadosNovaAquisicao != null) {
			// Dados da nova Aquisciao convertido em JSON.
			// Instanciar uma nova Aquisicao com os dados novo.
			Aquisicao aquisicao = new Aquisicao();
			// Adicionar os itens adquirido: componete, qtdAdquirida e o custo.
			for (ItemAquisicao item : dadosNovaAquisicao.getComponentes()) {
				aquisicao.addComponente(item.getComponente(), item.getQtdAdquirida(), item.getCusto());
			}
			// Persistir no BD a nova Aquisicao.
			if (getDao().salvar(aquisicao)) {
				// Nova Aquisicao foi criado e persistido com sucesso.
				setResposta(mensagemCriadoRecurso());
			} else {
				// Os dados informado possui campos inválidos.
				setResposta(mensagemDadosInvalidos());
			}
		} else {
			// Aconteceu um errro com o servidor.
			setResposta(mensagemErro());
		}
		return getResposta();
	}

	@Override
	public Response getRecurso(Integer id) {
		// Guarda os valores retornado do BD.
		Aquisicao resultado = getDao().buscarPor(id);
		if (resultado != null) {
			// Pega so componentes das aquisições retornado.
			List<ItemAquisicao> componentes = new ArrayList<>();
			for (ItemAquisicao item : resultado.getComponentes()) {
				componentes.add(new ItemAquisicao(item.getComponente(), item.getQtdAdquirida(), item.getCusto()));
			}
			// Instancia uma nova Aquisicao com os dados da Aquisicao retornado do BD.
			Aquisicao aquisicaoEmJava = new Aquisicao(resultado.getCodigo(), resultado.getDataAquisicao(), componentes);
			// Converter a nova Aquisicao para uma Sring em JSON.
			String aquisicaoEmJson = converterParaJSON(aquisicaoEmJava);
			// Retorna response com a Aquisicao em JSON.
			setResposta(mensagemSucesso(aquisicaoEmJson));
		} else {
			// Retorna response informando que o Id informado não possui cadastro.
			setResposta(mensagemNaoEncontrado());
		}
		return getResposta();
	}

	@Override
	public Response getRecursos() {
		// Guarda os valores retornado do BD.
		List<Aquisicao> lista = getDao().buscarTodos();
		if (lista != null) {
			// Criar uma nova lista para guardar os valores retornado do BD.
			List<Aquisicao> aquisicoes = new ArrayList<>();
			// Percorrer a lista com os valores retornado do BD.
			for (Aquisicao aquisicao : lista) {
				// Criar uma nova lista para guardar os componentes retornado do BD.
				List<ItemAquisicao> componentes = new ArrayList<>();
				// Pega os componentes de cada Aquisicao e add na nova lista de ItemAquisicao.
				for (ItemAquisicao item : aquisicao.getComponentes()) {
					componentes.add(new ItemAquisicao(item.getComponente(), item.getQtdAdquirida(), item.getCusto()));
				}
				// add na nova lista de aquisições uma nova Aquisicao com os dados da lista
				// percorrida.
				aquisicoes.add(new Aquisicao(aquisicao.getCodigo(), aquisicao.getDataAquisicao(), componentes));
			}
			// Convete a nova lista de aquisições para um ArrayJSON.
			String listaEmJson = converterParaArrayJSON(aquisicoes);
			// Retorna response com a lista de aquisições no formato ArrayJSON.
			setResposta(mensagemSucesso(listaEmJson));
		} else {
			// Retorna response informando que não possui aquisições cadastrado.
			setResposta(mensagemNaoEncontrado());
		}
		return getResposta();
	}

}
