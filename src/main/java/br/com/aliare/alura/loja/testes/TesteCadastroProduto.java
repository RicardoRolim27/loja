package br.com.aliare.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.aliare.alura.loja.dao.CategoriaDAO;
import br.com.aliare.alura.loja.dao.ProdutoDAO;
import br.com.aliare.alura.loja.modelo.Categoria;
import br.com.aliare.alura.loja.modelo.Produto;
import br.com.aliare.alura.loja.util.JPAUtil;

public class TesteCadastroProduto {

	public static void main(String[] args) {

		Categoria informatica = new Categoria("Informatica");
		EntityManager entityManager = JPAUtil.getEntityManager();
		CategoriaDAO categoriaDao = new CategoriaDAO(entityManager);
		
		Produto headset = new Produto("Hoopson", "headset sem fim bluetooth", 
				new BigDecimal("150.00"), informatica);
		
		ProdutoDAO dao = new ProdutoDAO(entityManager);
		
		entityManager.getTransaction().begin();	
		
		categoriaDao.cadastrar(informatica);
		dao.cadastrar(headset);
		
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
