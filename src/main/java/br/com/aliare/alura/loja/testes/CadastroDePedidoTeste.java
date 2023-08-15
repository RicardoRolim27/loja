package br.com.aliare.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.aliare.alura.loja.dao.CategoriaDAO;
import br.com.aliare.alura.loja.dao.ClienteDAO;
import br.com.aliare.alura.loja.dao.PedidoDAO;
import br.com.aliare.alura.loja.dao.ProdutoDAO;
import br.com.aliare.alura.loja.modelo.Categoria;
import br.com.aliare.alura.loja.modelo.Cliente;
import br.com.aliare.alura.loja.modelo.ItemPedido;
import br.com.aliare.alura.loja.modelo.Pedido;
import br.com.aliare.alura.loja.modelo.Produto;
import br.com.aliare.alura.loja.util.JPAUtil;

public class CadastroDePedidoTeste {
	
	public static void main(String[] args) throws Exception {
		//cadastrarProduto();
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		ProdutoDAO dao = new ProdutoDAO(entityManager);
		Produto produto = dao.buscarPorId(3l);
		
		//Cliente cliente = new Cliente("Roberto", "123456987");
		ClienteDAO clienteDao = new ClienteDAO(entityManager);
		Cliente cliente = clienteDao.buscarPorId(1l);
		
		Pedido pedido = new Pedido(cliente);
		
		pedido.adicionarItem(new ItemPedido(1, produto, pedido));
		
		PedidoDAO pedidoDao = new PedidoDAO(entityManager);
		
		entityManager.getTransaction().begin();
		
		//clienteDao.cadastrar(cliente);
		
		pedidoDao.cadastrar(pedido);

		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	
	
	private static void cadastrarProduto() {
		Categoria eletro = new Categoria("Eletrodomestico");
		EntityManager entityManager = JPAUtil.getEntityManager();
		CategoriaDAO categoriaDao = new CategoriaDAO(entityManager);
		
		Produto tv = new Produto("Smart tv 50", "Smart tv 50 pol. Samsung", 
				new BigDecimal("3500"), eletro);
		
		ProdutoDAO dao = new ProdutoDAO(entityManager);
		
		entityManager.getTransaction().begin();	
		
		categoriaDao.cadastrar(eletro);
		dao.cadastrar(tv);
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
