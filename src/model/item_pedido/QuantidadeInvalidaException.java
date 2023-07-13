package model.item_pedido;

public class QuantidadeInvalidaException extends Exception {
  public QuantidadeInvalidaException(String message) {
    super(message);
  }
}
//lança exceção para verificar se a quantidade é menor ou igual a zero