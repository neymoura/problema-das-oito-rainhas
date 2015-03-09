package br.neymoura.ia.rainhas.agente;

import com.google.gson.Gson;

public class Estado{

	public Estado(int[][] estado, int utilidade) {
		this.estado = estado;
		this.utilidade = utilidade;
	}

	public int[][] estado;
	public int utilidade;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	public boolean compareTo(Estado o) {
		
		boolean estadosIguais = true;
		
		for (int i = 0; i < estado.length; i++) {
			for (int j = 0; j < estado.length; j++) {
				if(this.estado[i][j] != o.estado[i][j]){
					estadosIguais = false;
				}
			}
		}
		
		return estadosIguais;
	}


}
