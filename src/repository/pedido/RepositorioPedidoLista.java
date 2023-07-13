package repository.pedido;

import java.util.ArrayList;
import java.util.List;

import model.pedido.StatusPedido;
import model.pedido.PedidoNaoEncontradoException;
import model.pedido.PedidoNaoEncontradoException;
import model.pedido.Pedido;
import model.pessoa.Cliente;
import model.pessoa.Entregador;
import model.pedido.StatusPedido;

import java.io.Serializable;

import java.util.Date;

import java.util.ListIterator;
import java.util.Iterator;

public class RepositorioPedidoLista implements RepositorioPedido, Serializable{

    private List<Pedido> pedidos;

    private transient Iterator<Pedido> pedidosIterator;

    public RepositorioPedidoLista() {
        pedidos = new ArrayList<>();
    }

    public void adicionarPedido(Pedido pedido) {
        pedido.setStatus(StatusPedido.FEITO);
        pedidos.add(pedido);
    }
    
    public void alterarPedido() {
    	
    }
    public void removerPedido(Pedido pedido) {
        pedidos.remove(pedido);
    }

    
    public List<Pedido> getAll() {
        return pedidos;
    }

    public Pedido buscarPedido(int id) throws PedidoNaoEncontradoException {
      for(Pedido p: pedidos ){
        if(p.getID()==id){
          return p;
        }
      }
      throw new PedidoNaoEncontradoException("Pedido Não Encontrado");
    }
    
    public List<Pedido> getAllPedidosPorCliente(String cpf) {
        List<Pedido> pedidosPorCliente = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getCliente().getCpf().equals(cpf)) {
                pedidosPorCliente.add(pedido);
            }
        }
        return pedidosPorCliente;
    }

    @Override
    public int getNewID(){
      this.pedidoIterator();
      
      int contar = 0;
      if (pedidos.isEmpty()){
        return (contar + 1);
      }
      contar ++;
      while (this.pedidosIterator.hasNext()){
          Pedido p = this.pedidosIterator.next();
          contar = Math.max(contar, p.getID());
          
      } 
      return (contar + 1);
    }

    
    public List<Pedido> getAllPedidosPorEntregador(String cpf) {
        List<Pedido> pedidosPorEntregador = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getEntregador().getCpf().equals(cpf)) {
                pedidosPorEntregador.add(pedido);
            }
        }
        return pedidosPorEntregador;
    }

  @Override
  public double calcularFaturamentoTotal(){
    double valorTotal = 0;
    this.pedidoIterator();
    while (this.pedidosIterator.hasNext()){
      Pedido p = this.pedidosIterator.next();
      if (p.getStatus() == StatusPedido.ENTREGUE)
      {
        valorTotal += (p.total() - p.desconto());
      }
    }
    return valorTotal;
  }

  @Override
  public double calcularFaturamentoDia(String data){
    double valorDia = 0;
    this.pedidoIterator();
    while (this.pedidosIterator.hasNext()){
      Pedido p = this.pedidosIterator.next();
      if (p.getStatus() == StatusPedido.ENTREGUE)
      {
        if (p.formatDate().equals(data))
          valorDia += (p.total() - p.desconto());
      }
    }
    return valorDia;
  }

  private void pedidoIterator(){
      //Atualiza caso for preciso
      this.pedidosIterator = pedidos.iterator();
  }
}