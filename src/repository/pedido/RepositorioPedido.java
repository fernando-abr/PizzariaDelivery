package repository.pedido;

import java.util.List;
import model.pedido.PedidoNaoEncontradoException;
import model.pedido.Pedido;
import model.pessoa.Cliente;
import model.pessoa.Entregador;

import java.util.Date;

public interface RepositorioPedido {
	
	void adicionarPedido(Pedido pedido);

  Pedido buscarPedido(int id) throws PedidoNaoEncontradoException;
	
	void removerPedido(Pedido pedido);
	
	List <Pedido> getAll();
	
	List <Pedido> getAllPedidosPorCliente(String cpf);
	
	List <Pedido> getAllPedidosPorEntregador(String cpf);

  double calcularFaturamentoTotal();

  int getNewID();

  double calcularFaturamentoDia(String data);
}
