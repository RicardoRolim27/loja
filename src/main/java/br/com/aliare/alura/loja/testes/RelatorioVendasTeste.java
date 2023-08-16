package br.com.aliare.alura.loja.testes;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.aliare.alura.loja.dao.PedidoDAO;
import br.com.aliare.alura.loja.util.JPAUtil;
import br.com.aliare.alura.loja.vo.RelatorioDeVendasVo;

public class RelatorioVendasTeste {

	public static void main(String[] args) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		PedidoDAO pedidoDao = new PedidoDAO(entityManager);
		
		List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
		
		relatorio.forEach(System.out::println);

	}

}
