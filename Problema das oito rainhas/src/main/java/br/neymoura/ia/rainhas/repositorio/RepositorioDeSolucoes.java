package br.neymoura.ia.rainhas.repositorio;

import java.util.ArrayList;
import java.util.List;

import br.neymoura.ia.rainhas.agente.Estado;
import br.neymoura.ia.rainhas.tabuleiro.ManipuladorTabuleiro;

public class RepositorioDeSolucoes {

	// para mais detalhes: http://pt.wikipedia.org/wiki/Problema_das_oito_damas
	public static int SOLUCOES_POSSIVEIS = 92;

	private List<Estado> solucoesEncontradas;

	public RepositorioDeSolucoes() {
		solucoesEncontradas = new ArrayList<Estado>();
	}

	public List<Estado> getSolucoesEncontradas() {
		return solucoesEncontradas;
	}

	public void salvaSolucao(Estado novaSolucao) {

		// verifica se solução já existe
		boolean solucoesIguais = false;

		for (Estado solucaoEncontrada : solucoesEncontradas) {

			if (solucaoEncontrada.compareTo(novaSolucao)) {
				solucoesIguais = true;
				break;
			}

		}

		// se não encontrou, adiciona em solucoes encontradas e grava no
		// arquivo.

		if (!solucoesIguais) {
			solucoesEncontradas.add(novaSolucao);
		}

	}

	public int contaSolucoesEncontradas() {
		return solucoesEncontradas.size();
	}

	public boolean isNumeroDeSolucoesSatisfatorio() {
		if (contaSolucoesEncontradas() == SOLUCOES_POSSIVEIS) {
			return true;
		} else {
			return false;
		}
	}

	public void imprimeSolucoesEncontradas() {

		System.out.println("------------------------------------------");
		System.out.println("------------------Soluções----------------");
		System.out.println("------------------------------------------");

		int contador = 0;

		for (Estado estado : solucoesEncontradas) {
			System.out.println("Solução " + (++contador) + ":");
			new ManipuladorTabuleiro(estado.estado).imprimeTabuleiro();
		}

		System.out.println("------------------------------------------");
		System.out.println("--------------Fim-Soluções----------------");
		System.out.println("------------------------------------------");
	}

}
