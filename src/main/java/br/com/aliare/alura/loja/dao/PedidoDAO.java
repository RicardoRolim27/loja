package br.com.aliare.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.aliare.alura.loja.modelo.Pedido;
import br.com.aliare.alura.loja.vo.RelatorioDeVendasVo;

public class PedidoDAO {

	private EntityManager entityManager;

	public PedidoDAO(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	public void cadastrar(Pedido pedido) {
		this.entityManager.persist(pedido);
	}

	public Pedido buscarPorId(Long id) throws Exception {
		if (id != null) {
			return entityManager.find(Pedido.class, id);
		}
		throw new Exception("Erro ao buscar um pedido, valor n�o pode ser nulo!");
	}
	
	public void remover(Pedido pedido) {

		if (pedido == null) {
			System.out.println("Erro ao tentar remover pedido, valor n�o pode ser nulo!");
		}

		try {
			pedido = this.entityManager.merge(pedido);

			this.entityManager.remove(pedido);
		} catch (Exception e) {
			System.out.println("N�o foi poss�vel remover o pedido");
			System.out.println(e.getMessage());
		}
	}
	
	public void atualizar(Pedido pedido) {
		this.entityManager.merge(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		
		return entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();
	}
	
	
	public List<RelatorioDeVendasVo> relatorioDeVendas() {
		String jpql = "select new br.com.aliare.alura.loja.vo.RelatorioDeVendasVo ("
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data)) "
				+ "from Pedido pedido "
				+ "join pedido.itens item "
				+ "join item.produto produto "
				+ "group by produto.nome "
				+ "order by SUM(item.quantidade) desc";
		
		return entityManager.createQuery(jpql, RelatorioDeVendasVo.class).getResultList();
	}


}
