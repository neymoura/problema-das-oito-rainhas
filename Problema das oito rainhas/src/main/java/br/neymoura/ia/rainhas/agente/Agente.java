package br.neymoura.ia.rainhas.agente;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import br.neymoura.ia.rainhas.tabuleiro.ManipuladorTabuleiro;
import br.neymoura.ia.rainhas.tabuleiro.Posicao;

/**
 * Implementação de um agente baseado em utilidade para resolver o problema do
 * posicionamento das oito rainhas no tabuleiro de xadrez.
 * 
 * @author Ney
 * @since 05/03/2015
 */
public class Agente {

	public Agente() {
		super();
	}

	public void executa() {

		System.out.println("--Agente iniciou sua execução--");

		LinkedList<Estado> filaDeEstados = new LinkedList<Estado>();

		Estado estadoInicial = criaEstadoInicial();

		filaDeEstados.add(estadoInicial);

		int iterationCounter = 0;

		while (!filaDeEstados.isEmpty()) {

			System.out.println("[" + (iterationCounter++)
					+ "]Agente executando... ");

			Estado estadoAtual = filaDeEstados.poll();

			System.out.println("Utilidade atual: " + estadoAtual.utilidade);

			if (testaEstadoObjetivo(estadoAtual.estado)) {

				System.out.println("\n-----Solução-----");
				new ManipuladorTabuleiro(estadoAtual.estado).imprimeTabuleiro();
				System.out.println("-----Solução-----\n");

				break;

			} else {
				new ManipuladorTabuleiro(estadoAtual.estado).imprimeTabuleiro();
			}

			List<Estado> estadosSucessores = funcaoSucessora(estadoAtual.estado);

			Estado sucessorMaisUtil = new Estado(
					new int[ManipuladorTabuleiro.DIMENSAO_PADRAO][ManipuladorTabuleiro.DIMENSAO_PADRAO],
					0);

			for (Estado estadoSucessor : estadosSucessores) {
				if (estadoSucessor.utilidade > sucessorMaisUtil.utilidade) {
					sucessorMaisUtil = estadoSucessor;
				}
			}

			estadosSucessores.remove(sucessorMaisUtil);
			
			filaDeEstados.addAll(0, estadosSucessores);
			filaDeEstados.addFirst(sucessorMaisUtil);

		}

		System.out.println("--Agente terminou sua execução--");

	}

	private Estado criaEstadoInicial() {

		Estado estadoInicial = new Estado(
				new int[ManipuladorTabuleiro.DIMENSAO_PADRAO][ManipuladorTabuleiro.DIMENSAO_PADRAO],
				0);

		estadoInicial.utilidade = utilidade(estadoInicial.estado);

		return estadoInicial;
	}

	/**
	 * Calcula os estados sucessores possíveis
	 * 
	 * @param estado
	 * @return List<Sucessor> com os possíveis estados e suas utilidades
	 */
	private List<Estado> funcaoSucessora(int[][] estado) {

		List<Estado> estadosSucessores = new ArrayList<Estado>();

		List<Posicao> estadosLivresESeguros = new ManipuladorTabuleiro(estado)
				.getPosicoesLivresESeguras();

		for (Posicao posicao : estadosLivresESeguros) {

			// efetua cópia do estado para uma variável temporária
			int[][] estadoTemp = new int[estado.length][estado.length];

			for (int i = 0; i < estadoTemp.length; i++) {
				for (int j = 0; j < estadoTemp.length; j++) {
					estadoTemp[i][j] = estado[i][j];
				}
			}
			// fim da cópia

			ManipuladorTabuleiro mTabuleiro = new ManipuladorTabuleiro(
					estadoTemp);

			if (mTabuleiro.posicionaRainha(posicao)) {
				Estado estadoSucessor = new Estado(mTabuleiro.getTabuleiro(),
						utilidade(mTabuleiro.getTabuleiro()));

				estadosSucessores.add(estadoSucessor);

			}

			mTabuleiro = null;

		}

		return estadosSucessores;
	}

	/**
	 * Calcula do grau de utilidade
	 * 
	 * @param estado
	 * @return int representando o grau de utilidade do estado. Quanto maior,
	 *         melhor.
	 */
	private int utilidade(int[][] estado) {

		// Heurística:
		// Estados melhores são aqueles que viabilizem
		// o posicionamento de mais rainhas.

		// Verifica se o estado informado é o objetivo
//		if (testaEstadoObjetivo(estado) == true) {
//			return Integer.MAX_VALUE;
//		}
//
//		return new ManipuladorTabuleiro(estado).getPosicoesLivresESeguras()
//				.size();

		Random random = new Random();

		return random.nextInt(10);

	}

	/**
	 * Testa se o estado é o objetivo
	 * 
	 * @param estado
	 * @return true se o estado é o objetivo, false caso contrário.
	 */
	private boolean testaEstadoObjetivo(int[][] estado) {

		// O objetivo é possuir n, igual a dimensao do tabuleiro, rainhas posicionadas no tabuleiro.

		if (new ManipuladorTabuleiro(estado).contaRainhasNoTabuleiro() == ManipuladorTabuleiro.DIMENSAO_PADRAO) {
			return true;
		} else {
			return false;
		}

	}

}