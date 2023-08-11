package br.com.aliare.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

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

	public Produto buscarPorId(Long id) throws Exception {
		if (id != null) {
			return entityManager.find(Produto.class, id);
		}
		throw new Exception("Erro ao buscar um produto, valor não pode ser nulo!");
	}

	public List<Produto> buscarTodos() {

		String jpql = "SELECT p FROM Produto p";
		return entityManager.createQuery(jpql, Produto.class).getResultList();
	}

	public List<Produto> buscarPorNome(String nome) {

		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return entityManager.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
	}

	public List<Produto> buscarPorNomeDaCategoria(String categoria) {

		String jpql = "SELECT p FROM Produto p WHERE p.categoriaId.nome = :categoria";
		return entityManager.createQuery(jpql, Produto.class).setParameter("categoria", categoria).getResultList();
	}

	public BigDecimal buscarPrecoProdutoPorNome(String nome) {

		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return entityManager.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}

	public void remover(Produto produto) {

		if (produto == null) {
			System.out.println("Erro ao tentar remover produto, valor não pode ser nulo!");
		}

		try {
			produto = this.entityManager.merge(produto);

			this.entityManager.remove(produto);
		} catch (Exception e) {
			System.out.println("Não foi possível remover o produto");
			System.out.println(e.getMessage());
		}
	}

}
