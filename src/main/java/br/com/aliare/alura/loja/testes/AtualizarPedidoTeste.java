package br.com.aliare.alura.loja.testes;

import javax.persistence.EntityManager;

import br.com.aliare.alura.loja.dao.PedidoDAO;
import br.com.aliare.alura.loja.modelo.Pedido;
import br.com.aliare.alura.loja.util.JPAUtil;

public class AtualizarPedidoTeste {

	public static void main(String[] args) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		PedidoDAO pedidoDao = new PedidoDAO(entityManager);
		
		try {
			Pedido pedido = pedidoDao.buscarPorId(2l);
			pedido.setValorTotal(pedido.getValorTotal());
			
			pedidoDao.atualizar(pedido);
			
		} catch (Exception e) {
			System.out.println("Não foi possível buscar o pedido!");
			System.out.println(e.getMessage());
		}

	}

}
