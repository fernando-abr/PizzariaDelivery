
package model.pedido;

import model.pedido.StatusPedido;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import model.pessoa.Entregador;
import model.pessoa.Pessoa;
import model.item_pedido.ItemPedido;
import model.item_pedido.ProdutoAusenteException;
import model.pessoa.Cliente;
import model.produto.Produto;
import java.util.ListIterator;

import java.text.SimpleDateFormat;
import java.io.Serializable; 

public class Pedido implements Serializable{
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

  private int id;
	private Date momentoPedido;
	private Cliente cliente;
	private Entregador entregador;
  private String endereco;
  private StatusPedido status;
  
	private List<ItemPedido> itens = new ArrayList<>();
  private transient Iterator<ItemPedido> itensIterator;
  
	public Pedido(Cliente cliente, Entregador entregador, String endereco, int id) {
    this.id = id;
    this.endereco = endereco;
    this.status = StatusPedido.AGUARDANDO_CONFIRMACAO;
		this.momentoPedido = new Date();
		this.cliente = cliente;
		this.entregador = entregador;
    itensIterator = itens.iterator();
	}

  public String getEndereco (){
    return endereco;
  }

  public void setEndereco(String endereco){
    this.endereco = endereco;
  }

  public int getID(){
    return id;
  }

  public void setID(int id){
    this.id = id;
  }

  public StatusPedido getStatus(){
    return status;
  }

  public void setStatus(StatusPedido status){
    this.status = status;
  }
	
	public Date getMomentoPedido() {
		return momentoPedido;
	}

	public void setMomentoPedido(Date momentoPedido) {
		this.momentoPedido = momentoPedido;
	}
  
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Entregador getEntregador() {
		return entregador;
	}

  public String formatDate() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        return sdf1.format(this.momentoPedido);
    }


	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}

  public void adicionarItem(ItemPedido item) throws ProdutoAusenteException {
    if(item.getProduto() == null ){
      throw new ProdutoAusenteException("Produto Inexistente!");
    }
    itens.add(item);
  }

  public void removerItem(ItemPedido item){
    itens.remove(item);
  }

  public double total() {
	  
	  double total = 0.0;
	  for (ItemPedido i : itens) {
		  total = total + i.subTotal();
	  }
	  return total;
  }
  
  public double desconto() {
    double valorDesconto = 0.001*total();
    return valorDesconto;
  }

  public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("----------------------------");
		sb.append("\nCOMPROVANTE DE VENDA\n");
		sb.append(cliente.getNome());
		sb.append("\n\nData: ");
		sb.append(sdf.format(momentoPedido));
    sb.append("\nEndereço: ");
    sb.append(endereco);
		sb.append("\nProdutos:\n");
		for (ItemPedido i : itens) {
			sb.append(i);
		}
		sb.append("\n\nTotal: ");
		sb.append(String.format("R$ %.2f\n", total()));
		sb.append("Desconto: ");
		sb.append(String.format("R$ %.2f\n", desconto()));
		sb.append("Total a pagar: ");
		sb.append(String.format("R$ %.2f\n", total()-desconto()));
		return sb.toString();
	}

  private void itensIterator(){
    this.itensIterator = itens.iterator();
  }
}