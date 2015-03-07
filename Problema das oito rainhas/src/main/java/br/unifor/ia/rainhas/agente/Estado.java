package br.unifor.ia.rainhas.agente;

import com.google.gson.Gson;

public class Estado {
	
	public Estado(int[][] estado, int utilidade){
		this.estado = estado;
		this.utilidade = utilidade;
	}
	
	int[][] estado;
	int utilidade;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
