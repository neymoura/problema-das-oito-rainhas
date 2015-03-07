package br.unifor.ia.rainhas.tabuleiro;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe ManipuladorTabuleiro é responsável por
 * executar as interações com o tabuleiro.
 * 
 * @author Ney
 * @since 05/03/2015
 */
public class ManipuladorTabuleiro {

	public static final int DIMENSAO_PADRAO = 8;

	private static final int POSICAO_LIVRE = 0;
	private static final int POSICAO_RAINHA = 1;
	private static final int POSICAO_EM_ATAQUE = 2;

	private static final boolean MASCARAR_POS_ATAQUE = true;
	private static final String MASCARA_POS_ATAQUE = "*";

	private int[][] tabuleiro;
	
	public ManipuladorTabuleiro(int[][] tabuleiro){
		this.tabuleiro = tabuleiro;
	}

	public int[][] getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(int[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public List<Posicao> getPosicoesLivresESeguras() {

		List<Posicao> posicoesLivresESeguras = new ArrayList<Posicao>();

		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {

				if (tabuleiro[i][j] == POSICAO_LIVRE) {
					posicoesLivresESeguras.add(new Posicao(i, j));
				}

			}
		}

		return posicoesLivresESeguras;

	}

	public boolean posicionaRainha(Posicao posicao) {

		boolean movimentoPossivel = false;

		for (Posicao posLivreESegura : getPosicoesLivresESeguras()) {
			if (posicao.x == posLivreESegura.x && posicao.y == posLivreESegura.y) {
				movimentoPossivel = true;
			}
		}

		if (!movimentoPossivel) {
			return false;
		}

		preencheAreaDeAtaqueDaRainha(posicao);

		tabuleiro[posicao.x][posicao.y] = POSICAO_RAINHA;

		return true;
	}

	private void preencheAreaDeAtaqueDaRainha(Posicao posicao) {

		// desenha linha de ataque vertical e horizontal
		for (int i = 0; i < tabuleiro.length; i++) {
			tabuleiro[posicao.x][i] = POSICAO_EM_ATAQUE;
			tabuleiro[i][posicao.y] = POSICAO_EM_ATAQUE;
		}

		// desenha linha de ataque nas diagonais
		int secoesDiagonaisCompletas = 0;

		while (secoesDiagonaisCompletas < 4) {

			int i = posicao.x;
			int j = posicao.y;

			switch (secoesDiagonaisCompletas) {

			case 0:

				try {

					while (true) {
						tabuleiro[--i][--j] = POSICAO_EM_ATAQUE;
					}

				} catch (Exception e) {
					secoesDiagonaisCompletas += 1;
					break;
				}

			case 1:

				try {

					while (true) {
						tabuleiro[++i][--j] = POSICAO_EM_ATAQUE;
					}

				} catch (Exception e) {
					secoesDiagonaisCompletas += 1;
					break;
				}

			case 2:

				try {

					while (true) {
						tabuleiro[--i][++j] = POSICAO_EM_ATAQUE;
					}

				} catch (Exception e) {
					secoesDiagonaisCompletas += 1;
					break;
				}

			case 3:

				try {

					while (true) {
						tabuleiro[++i][++j] = POSICAO_EM_ATAQUE;
					}

				} catch (Exception e) {
					secoesDiagonaisCompletas += 1;
					break;
				}

			default:
				break;
			}

		}

	}

	public int contaRainhasNoTabuleiro() {

		int contador = 0;

		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {

				if (tabuleiro[i][j] == POSICAO_RAINHA) {
					contador++;
				}

			}
		}

		return contador;
	}

	public boolean isTabuleiroVazio(){
		
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {
				if(tabuleiro[i][j] != POSICAO_LIVRE){
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	public void imprimeTabuleiro() {

		System.out.println();
		System.out.println("*****");

		System.out.println("Posições Livres: "
				+ getPosicoesLivresESeguras().size());
		System.out
				.println("Rainhas no Tabuleiro: " + contaRainhasNoTabuleiro());

		System.out.println();

		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {

				if (tabuleiro[i][j] == POSICAO_EM_ATAQUE && MASCARAR_POS_ATAQUE) {
					System.out.print(MASCARA_POS_ATAQUE + "\t");
				} else {
					System.out.print(tabuleiro[i][j] + "\t");
				}

			}
			System.out.println();
		}

		System.out.println("*****");
		System.out.println();

	}
	
}