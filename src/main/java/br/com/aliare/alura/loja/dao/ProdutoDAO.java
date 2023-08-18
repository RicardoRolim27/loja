package br.com.aliare.alura.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
		throw new Exception("Erro ao buscar um produto, valor n�o pode ser nulo!");
	}
	
	public Produto buscarUmProdutoPorNome(String nome) throws Exception {
		if (nome != null) {
			String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
			return entityManager.createQuery(jpql, Produto.class).setParameter("nome", nome).getSingleResult();
		}
		throw new Exception("Erro ao buscar um produto, valor n�o pode ser nulo!");
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

		//String jpql = "SELECT p FROM Produto p WHERE p.categoriaId.nome = :categoria";
		return entityManager.createNamedQuery("Produto.produtosPorCategoria", Produto.class).setParameter("categoria", categoria).getResultList();
	}

	public BigDecimal buscarPrecoProdutoPorNome(String nome) {

		String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return entityManager.createQuery(jpql, BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}

	public void remover(Produto produto) {

		if (produto == null) {
			System.out.println("Erro ao tentar remover produto, valor n�o pode ser nulo!");
		}

		try {
			produto = this.entityManager.merge(produto);

			this.entityManager.remove(produto);
		} catch (Exception e) {
			System.out.println("N�o foi poss�vel remover o produto");
			System.out.println(e.getMessage());
		}
	}
	
	public List<Produto> buscarPorParametroDinamico(String nome, BigDecimal preco, LocalDate data){
		
		String jpql = "select p from Produto p where 1=1";
		
		if(nome !=null && !nome.trim().isEmpty()) {
			jpql += " and p.nome = :nome";
		}
		
		if(preco !=null) {
			jpql += " and p.preco = :preco";
		}
		
		if(data !=null) {
			jpql += " and p.dataCadastro = :data";
		}
		
		
		TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);
		
		if(nome !=null && !nome.trim().isEmpty()) {
			query.setParameter("nome", nome);
		}
		
		if(preco !=null) {
			query.setParameter("preco", preco);
		}
		
		if(data !=null) {
			query.setParameter("data", data);
		}
		
		return query.getResultList();				
				
	}
	
	public List<Produto> buscaPorParametoComCriteria(String nome, BigDecimal preco, LocalDate data){
		
		CriteriaBuilder criteria = entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> query = criteria.createQuery(Produto.class);
		
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filtro = criteria.and();
		
		if(nome !=null && !nome.trim().isEmpty()) {
			filtro = criteria.and(filtro, criteria.equal(from.get("nome"), nome));
		}
		
		if(preco !=null) {
			filtro = criteria.and(filtro, criteria.equal(from.get("preco"), preco));
		}
		
		if(data !=null) {
			filtro = criteria.and(filtro, criteria.equal(from.get("dataCadastro"), data));
		}
		
		query.where(filtro);		
		
		return entityManager.createQuery(query).getResultList();
	}

}
