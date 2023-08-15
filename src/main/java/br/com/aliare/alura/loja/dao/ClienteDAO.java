package br.com.aliare.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.aliare.alura.loja.modelo.Cliente;

public class ClienteDAO {

	private EntityManager entityManager;

	public ClienteDAO(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	public void cadastrar(Cliente cliente) {
		this.entityManager.persist(cliente);
	}

	public Cliente buscarPorId(Long id) throws Exception {
		if (id != null) {
			return entityManager.find(Cliente.class, id);
		}
		throw new Exception("Erro ao buscar um cliente, valor n�o pode ser nulo!");
	}
	
	public void remover(Cliente cliente) {

		if (cliente == null) {
			System.out.println("Erro ao tentar remover pedido, valor n�o pode ser nulo!");
		}

		try {
			cliente = this.entityManager.merge(cliente);

			this.entityManager.remove(cliente);
		} catch (Exception e) {
			System.out.println("N�o foi poss�vel remover o cliente");
			System.out.println(e.getMessage());
		}
	}

}
