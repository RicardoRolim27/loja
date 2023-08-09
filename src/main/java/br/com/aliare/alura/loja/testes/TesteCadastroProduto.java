package br.com.aliare.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.aliare.alura.loja.modelo.Produto;

public class TesteCadastroProduto {

	public static void main(String[] args) {
		
		Produto headset = new Produto();
		headset.setDescricao("headset sem fim bluetooth");
		headset.setNome("Hoopson");
		headset.setPreco(new BigDecimal("150.00"));
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
		
		EntityManager entityManager = factory.createEntityManager();
		
		entityManager.getTransaction().begin();	
		entityManager.persist(headset);
		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
