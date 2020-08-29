package teste;

import br.com.sigevendees.entity.Componente;
import br.com.sigevendees.entity.Receita;
import br.com.sigevendees.enums.CategoryTypes;
import br.com.sigevendees.enums.MeasureUnits;

public class TestaReceita {

	public static void main(String[] args) {
		// TESTA A CLASS RECEITA.
		
		// Pega dados da receita.
		int rendimento = 1;
		float tempPreparo = 0.30f;
		//Cria uma nova receita;
		Receita receita = new Receita(rendimento, tempPreparo);
		//Pega a lista de componentes.
		Componente farinha = new Componente("Farinha de trigo", CategoryTypes.INGREDIENTE, MeasureUnits.GRAMA, 1);
		Componente leite = new Componente("Leite integral", CategoryTypes.INGREDIENTE, MeasureUnits.MILILITRO, 1);
		Componente pote = new Componente("Pote descartável", CategoryTypes.EMBALAGEM, MeasureUnits.UNIDADE, 1);
		//Adiciona na lista de componentes da receita.
		receita.addComponente(farinha, 250);
		receita.addComponente(leite, 1);
		receita.addComponente(pote, 1);
		
		System.out.println(receita);

	}
}
