package br.com.aliare.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.aliare.alura.loja.dao.CategoriaDAO;
import br.com.aliare.alura.loja.dao.ProdutoDAO;
import br.com.aliare.alura.loja.modelo.Categoria;
import br.com.aliare.alura.loja.modelo.Produto;
import br.com.aliare.alura.loja.util.JPAUtil;

public class TesteCadastroProduto {

	public static void main(String[] args) throws Exception {

		//cadastrarProduto();
		
		Long id = 2l;
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(entityManager);
		
		Produto produto = dao.buscarPorId(id);
		System.out.println(produto);
		
		
		entityManager.getTransaction().begin();
		
		//dao.remover(produto);
		
		List<Produto> produtos = dao.buscarPorNomeDaCategoria("Informatica");
		
		BigDecimal preco = dao.buscarPrecoProdutoPorNome("Hoopson");
		
		System.out.println("O preço do produto é " + preco);
		
		produtos.forEach(p -> System.out.println(p.getNome()));
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	private static void cadastrarProduto() {
		Categoria informatica = new Categoria("Informatica");
		EntityManager entityManager = JPAUtil.getEntityManager();
		CategoriaDAO categoriaDao = new CategoriaDAO(entityManager);
		
		Produto headset = new Produto("Mouse Multilaser", "Mouse gamer Multilaser", 
				new BigDecimal("85.00"), informatica);
		
		ProdutoDAO dao = new ProdutoDAO(entityManager);
		
		entityManager.getTransaction().begin();	
		
		categoriaDao.cadastrar(informatica);
		dao.cadastrar(headset);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
