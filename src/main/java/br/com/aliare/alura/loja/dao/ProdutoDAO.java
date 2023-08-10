package br.com.aliare.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.aliare.alura.loja.modelo.Produto;

public class ProdutoDAO {
	
	private EntityManager entityManager;
	
	public ProdutoDAO(EntityManager entityManager) {
		
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Produto produto) {
		this.entityManager.persist(produto);
	}

}
