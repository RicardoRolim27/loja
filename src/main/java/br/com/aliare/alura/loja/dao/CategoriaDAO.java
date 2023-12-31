package br.com.aliare.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.aliare.alura.loja.modelo.Categoria;

public class CategoriaDAO {
	
private EntityManager entityManager;
	
	public CategoriaDAO(EntityManager entityManager) {
		
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Categoria categoria) {
		this.entityManager.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		this.entityManager.merge(categoria);
	}
	
	public void remover(Categoria categoria) {
		categoria = entityManager.merge(categoria);
		this.entityManager.remove(categoria);
	}
	
}
