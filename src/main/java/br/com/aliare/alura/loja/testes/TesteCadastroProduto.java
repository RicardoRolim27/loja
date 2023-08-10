package br.com.aliare.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.aliare.alura.loja.dao.ProdutoDAO;
import br.com.aliare.alura.loja.modelo.Produto;
import br.com.aliare.alura.loja.util.JPAUtil;

public class TesteCadastroProduto {

	public static void main(String[] args) {
		
		Produto headset = new Produto();
		headset.setDescricao("headset sem fim bluetooth");
		headset.setNome("Hoopson");
		headset.setPreco(new BigDecimal("150.00"));
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		ProdutoDAO dao = new ProdutoDAO(entityManager);
		
		entityManager.getTransaction().begin();	
		dao.cadastrar(headset);	
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
