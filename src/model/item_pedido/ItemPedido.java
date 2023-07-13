
package model.item_pedido;
import model.produto.Produto;
import model.item_pedido.QuantidadeInvalidaException;
import model.item_pedido.ProdutoAusenteException;

import java.io.Serializable; 

public class ItemPedido implements Serializable{

  private Produto produto;
  private int quantidade;
  private String observacao;
  
  public ItemPedido (Produto produto, int quantidade, String observacao)  throws QuantidadeInvalidaException {
    if(quantidade <= 0){
       throw new QuantidadeInvalidaException("Quantidade inv�lida");
    }
    this.produto = produto;
    this.quantidade = quantidade;
    this.observacao = observacao;
  }

  public Produto getProduto(){
    return this.produto;
  }
  public int getQuantidade(){
    return this.quantidade;
  }
  public void setProduto(Produto produto){
    this.produto = produto;
  }
  public void setQuantidade(int quantidade){
    this.quantidade = quantidade;
  }

  public void getObservacao(Produto produto){
    this.produto = produto;
  }
  public void setObservacao(String observacao){
    this.observacao = observacao;
  }

  public double subTotal(){
    return quantidade * produto.getPreco();
  }
  //toString retorna o objeto em uma string
  public String toString(){
    return "\nID do Produto: "
      +produto.getId()
      +", Nome do Produto: "
      +produto.getNome()
      +", Quantidade: "
      + quantidade
      +", Pre�o: "
      +String.format("R$ %.2f", produto.getPreco())
      +"\nDescri��o: "
      +produto.getDescricao()
      +", Subtotal: "
      +String.format("R$ %.2f\n", subTotal())
      +"\nObserva��o: "
      + observacao;
  }
}