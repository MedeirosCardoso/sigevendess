package teste;

import org.hibernate.Session;
import br.com.sigevendees.connectionFactory.FactoryHibernate;

public class TestaConexao {

	public static void main(String[] args) {
		Session conexao = FactoryHibernate.getSessionFactory().openSession();

		if (conexao != null) {
			System.out.println("CONEX�O REALIZADA COM SUCESSO!");
		} else {
			System.out.println("N�O FOI POSSIVEL CONECTAR AO BANCO DE DADOS!");
		}
	}
}