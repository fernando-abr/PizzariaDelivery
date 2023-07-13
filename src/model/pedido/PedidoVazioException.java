package model.pedido;

public class PedidoVazioException extends Exception {
  public PedidoVazioException(String message) {
    super(message);
  }
}
// lança exceção pra ver se a lista de pedido está vazia antes de calcular o total