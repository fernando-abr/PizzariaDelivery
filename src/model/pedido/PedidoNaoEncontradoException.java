package model.pedido;

public class PedidoNaoEncontradoException extends Exception {
  public PedidoNaoEncontradoException(String message) {
    super(message);
  }
}